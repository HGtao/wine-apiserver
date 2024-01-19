package com.wine.domain.bo;

import com.wine.domain.WineProducts;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品业务对象 wine_products
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = WineProducts.class, reverseConvertGenerate = false)
public class WineProductsBo extends BaseEntity {

    /**
     * 商品表主键id
     */
    @NotNull(message = "商品表主键id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 售卖价格
     */
    @NotNull(message = "售卖价格不能为空", groups = { AddGroup.class, EditGroup.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "售卖价格不能为负数", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;

    /**
     * 进货价格
     */
    @NotNull(message = "进货价格不能为空", groups = { AddGroup.class, EditGroup.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "进货价格不能为负数", groups = { AddGroup.class, EditGroup.class })

    private BigDecimal purchasePrice;

    /**
     * 是否为赠送商品(0为非赠送，1为赠送)
     */
    @NotNull(message = "是否为赠送商品不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer isGift;

    /**
     * 商品类型(1 白酒 2 饮料 3啤酒)
     */
    @NotNull(message = "商品类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productType;

    /**
     * 首月返现金额
     */
    @NotNull(message = "首月返现金额不能为空", groups = { AddGroup.class, EditGroup.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "首月返现金额不能为负数", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal firstMonthAmount;

    /**
     * 次月返现金额
     */
    @NotNull(message = "次月返现金额不能为空", groups = { AddGroup.class, EditGroup.class })
    @DecimalMin(value = "0.0", inclusive = false, message = "次月返现金额不能为负数", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal secondMonthAmount;

    /**
     * 商品编号
     */
    private String productNumber;

    /**
     * 备注
     */
    private String remark;
}
