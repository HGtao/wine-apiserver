package com.wine.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.wine.domain.WineProducts;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 商品视图对象 wine_products
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = WineProducts.class)
public class WineProductsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品表主键id
     */
    @ExcelProperty(value = "商品表主键id")
    private Long id;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String name;

    /**
     * 售卖价格
     */
    @ExcelProperty(value = "售卖价格")
    private BigDecimal price;

    /**
     * 进货价格
     */
    @ExcelProperty(value = "进货价格")
    private BigDecimal purchasePrice;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private String productNumber;

    /**
     * 是否为赠送商品(字典)
     */
    @ExcelProperty(value = "是否为赠送商品(0为非赠送，1为赠送)")
    private Integer isGift;

    /**
     * 商品类型(字典)
     */
    @ExcelProperty(value = "商品类型(1 白酒 2 饮料 3啤酒)")
    private Long productType;

    /**
     * 首月返现金额
     */
    @ExcelProperty(value = "首月返现金额")
    private BigDecimal firstMonthAmount;

    /**
     * 次月返现金额
     */
    @ExcelProperty(value = "次月返现金额")
    private BigDecimal secondMonthAmount;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
