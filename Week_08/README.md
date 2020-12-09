# 学习笔记

### 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，

1..   配置shardingsphere-proxy: config-sharding.yaml
```yaml
schemaName: sharding_db
#
dataSourceCommon:
  username: root
  password:
  connectionTimeoutMilliseconds: 30000
  idleTimeoutMilliseconds: 60000
  maxLifetimeMilliseconds: 1800000
  maxPoolSize: 50
  minPoolSize: 1
  maintenanceIntervalMilliseconds: 30000
#
dataSources:
  ds_0:
    url: jdbc:mysql://127.0.0.1:3306/my_shop?serverTimezone=UTC&useSSL=false
  ds_1:
    url: jdbc:mysql://127.0.0.1:3326/my_shop?serverTimezone=UTC&useSSL=false
#
rules:
- !SHARDING
  tables:
    t_order:
      actualDataNodes: ds_${0..1}.t_order_${0..15}
      tableStrategy:
        standard:
          shardingColumn: id
          shardingAlgorithmName: t_order_inline
      keyGenerateStrategy:
        column: id
        keyGeneratorName: snowflake
    t_order_item:
      actualDataNodes: ds_${0..1}.t_order_item_${0..15}
      tableStrategy:
        standard:
          shardingColumn: order_id
          shardingAlgorithmName: t_order_item_inline
      keyGenerateStrategy:
        column: id
        keyGeneratorName: snowflake
  bindingTables:
    - t_order,t_order_item
  defaultDatabaseStrategy:
    standard:
      shardingColumn: member_id
      shardingAlgorithmName: database_inline
  defaultTableStrategy:
    none:
#  
  shardingAlgorithms:
    database_inline:
      type: INLINE
      props:
        algorithm-expression: ds_${member_id % 2}
    t_order_inline:
      type: INLINE
      props:
        algorithm-expression: t_order_${id % 16}
    t_order_item_inline:
      type: INLINE
      props:
        algorithm-expression: t_order_item_${order_id % 16}
#  
  keyGenerators:
    snowflake:
      type: SNOWFLAKE
      props:
        worker-id: 123
```
server.yaml中配置
```yaml
authentication:
  users:
    root:
      password: root
    sharding:
      password: sharding
      authorizedSchemas: sharding_db
props:
  sql-show: true
```

2..执行sql
```sql
mysql -uroot -hlocalhost -P3307 -proot

CREATE TABLE t_order (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  member_id bigint(20) DEFAULT NULL,
  order_sn varchar(64) DEFAULT NULL COMMENT '订单编号',
  create_time datetime DEFAULT NULL COMMENT '提交时间',
  member_username varchar(512) DEFAULT NULL COMMENT '用户帐号',
  total_amount decimal(10,2) DEFAULT NULL COMMENT '订单总金额',
  pay_amount decimal(10,2) DEFAULT '0.00' COMMENT '应付金额（实际支付金额）',
  freight_amount decimal(10,2) DEFAULT NULL COMMENT '运费金额',
  pay_type int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信',
  source_type int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单',
  status int(1) DEFAULT NULL COMMENT '订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单',
  order_type int(1) DEFAULT NULL COMMENT '订单类型：0->正常订单；1->秒杀订单',
  auto_confirm_day int(11) DEFAULT NULL COMMENT '自动确认时间（天）',
  note varchar(500) DEFAULT NULL COMMENT '订单备注',
  confirm_status int(1) DEFAULT NULL COMMENT '确认收货状态：0->未确认；1->已确认',
  delete_status int(1) NOT NULL DEFAULT '0' COMMENT '删除状态：0->未删除；1->已删除',
  use_integration int(11) DEFAULT NULL COMMENT '下单时使用的积分',
  payment_time datetime DEFAULT NULL COMMENT '支付时间',
  modify_time datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4  ROW_FORMAT=DYNAMIC COMMENT='订单表'

CREATE TABLE `t_order_item` (
  `id` bigint(20) NOT NULL  ,
  `member_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `product_id` bigint(20) DEFAULT NULL,
  `product_pic` varchar(500) DEFAULT NULL,
  `product_name` varchar(200) DEFAULT NULL,
  `product_brand` varchar(200) DEFAULT NULL,
  `product_sn` varchar(64) DEFAULT NULL,
  `product_price` decimal(10,2) DEFAULT NULL COMMENT '销售价格',
  `product_quantity` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_sku_id` bigint(20) DEFAULT NULL COMMENT '商品sku编号',
  `product_sku_code` varchar(50) DEFAULT NULL COMMENT '商品sku条码',
  `product_category_id` bigint(20) DEFAULT NULL COMMENT '商品分类id',
  `sp1` varchar(100) DEFAULT NULL COMMENT '商品的销售属性',
  `sp2` varchar(100) DEFAULT NULL,
  `sp3` varchar(100) DEFAULT NULL,
  `product_attr` varchar(500) DEFAULT NULL COMMENT '商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]',
  `status` int(4) DEFAULT NULL,
  `type` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单中所包含的商品'
``` 

3.. 使用springboot mybatis 连接shardingsphere-proxy 进行增删改查操作 
- https://github.com/fengzhenbing/JAVA-000/tree/main/Week_08/ssproxy-sharding
 