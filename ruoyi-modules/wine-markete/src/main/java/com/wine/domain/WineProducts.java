package com.wine.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;

import java.io.Serial;

/**
 * 商品对象 wine_products
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("wine_products")
public class WineProducts extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品表主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 售卖价格
     */
    private BigDecimal price;

    /**
     * 进货价格
     */
    private BigDecimal purchasePrice;

    /**
     * 商品编号
     */
    private String productNumber;

    /**
     * 是否为赠送商品(字典)
     */
    private Integer gift;

    /**
     * 商品类型(字典)
     */
    private Long productType;

    /**
     * 首月返现金额
     */
    private BigDecimal firstMonthAmount;

    /**
     * 次月返现金额
     */
    private BigDecimal secondMonthAmount;

    /**
     * 备注
     */
    private String remark;


}
