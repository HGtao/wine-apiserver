package com.wine.service;

import com.wine.domain.bo.WineProductsBo;
import com.wine.domain.vo.WineProductsVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品Service接口
 *
 * @author Lion Li
 * @date 2024-01-16
 */
public interface IWineProductsService {

    /**
     * 查询商品
     */
    WineProductsVo queryById(Long id);

    /**
     * 查询商品列表
     */
    TableDataInfo<WineProductsVo> queryPageList(WineProductsBo bo, PageQuery pageQuery);

    /**
     * 查询商品列表
     */
    List<WineProductsVo> queryList(WineProductsBo bo);

    /**
     * 新增商品
     */
    Boolean insertByBo(WineProductsBo bo);

    /**
     * 修改商品
     */
    Boolean updateByBo(WineProductsBo bo);

    /**
     * 校验并批量删除商品信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
