# 学习笔记
### 批量插入100万条数据
 - 加参数rewriteBatchedStatements允许批量插入，增加max_allowed_packet传输容量，分10次，每次100000条，耗时**32**s
 - 在以上基础上，使用线程池，并行执行，时间大约**16**s, 效果好了一倍
 
### 读写分离 - 动态切换数据源版本 1.0
 - 使用spring多数据源，结合注解加aop来指定当前使用哪个数据源，通过AbstractRoutingDataSource.determineCurrentLookupKey设置

### 读写分离 - 读写分离 - 数据库框架版本 2.0 
 - 使用shardingsphere jdbc 4.0 实现读写分离
 - 后面继续升级到5.0最新版本