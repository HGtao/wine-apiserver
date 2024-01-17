package com.wine.mapper;

import com.wine.domain.WineProducts;
import com.wine.domain.vo.WineProductsVo;
import org.apache.ibatis.annotations.Mapper;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;

/**
 * 商品Mapper接口
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@Mapper
public interface WineProductsMapper extends BaseMapperPlus<WineProducts, WineProductsVo> {

}
