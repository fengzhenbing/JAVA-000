package org.fzb.ssproxySharding.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

/**
 * order_item
 * @author fengzhenbing
 */
@Data
public class OrderItemDO  extends BaseDO{

    private Long memberId;

    /**
     * 订单id
     */
    private BigInteger orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    private Long productId;

    private String productPic;

    private String productName;

    private String productBrand;

    private String productSn;

    /**
     * 销售价格
     */
    private BigDecimal productPrice;

    /**
     * 购买数量
     */
    private Integer productQuantity;

    /**
     * 商品sku编号
     */
    private Long productSkuId;

    /**
     * 商品sku条码
     */
    private String productSkuCode;

    /**
     * 商品分类id
     */
    private Long productCategoryId;

    /**
     * 商品的销售属性
     */
    private String sp1;

    private String sp2;

    private String sp3;

    /**
     * 商品销售属性:[{"key":"颜色","value":"颜色"},{"key":"容量","value":"4G"}]
     */
    private String productAttr;

    private Integer status;

    private Integer type;

    private static final long serialVersionUID = 1L;
}