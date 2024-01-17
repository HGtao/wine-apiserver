package com.wine.service.impl;

import cn.hutool.core.lang.generator.SnowflakeGenerator;
import com.wine.domain.WineProducts;
import com.wine.domain.bo.WineProductsBo;
import com.wine.domain.vo.WineProductsVo;
import com.wine.mapper.WineProductsMapper;
import com.wine.service.IWineProductsService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品Service业务层处理
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@RequiredArgsConstructor
@Service
public class WineProductsServiceImpl implements IWineProductsService {

    private final WineProductsMapper baseMapper;

    /**
     * 查询商品
     */
    @Override
    public WineProductsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品列表
     */
    @Override
    public TableDataInfo<WineProductsVo> queryPageList(WineProductsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<WineProducts> lqw = buildQueryWrapper(bo);
        Page<WineProductsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品列表
     */
    @Override
    public List<WineProductsVo> queryList(WineProductsBo bo) {
        LambdaQueryWrapper<WineProducts> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<WineProducts> buildQueryWrapper(WineProductsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<WineProducts> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), WineProducts::getName, bo.getName());
        lqw.eq(bo.getPrice() != null, WineProducts::getPrice, bo.getPrice());
        lqw.eq(bo.getPurchasePrice() != null, WineProducts::getPurchasePrice, bo.getPurchasePrice());
        lqw.eq(bo.getIsGift() != null, WineProducts::getIsGift, bo.getIsGift());
        lqw.eq(bo.getProductType() != null, WineProducts::getProductType, bo.getProductType());
        lqw.eq(bo.getFirstMonthAmount() != null, WineProducts::getFirstMonthAmount, bo.getFirstMonthAmount());
        lqw.eq(bo.getSecondMonthAmount() != null, WineProducts::getSecondMonthAmount, bo.getSecondMonthAmount());
        return lqw;
    }

    /**
     * 新增商品
     */
    @Override
    public Boolean insertByBo(WineProductsBo bo) {
        WineProducts add = MapstructUtils.convert(bo, WineProducts.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品
     */
    @Override
    public Boolean updateByBo(WineProductsBo bo) {
        WineProducts update = MapstructUtils.convert(bo, WineProducts.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(WineProducts entity){
        // 对商品名称做唯一约束
        if(StringUtils.isNotBlank(entity.getName())){
            WineProducts query = new WineProducts();
            query.setName(entity.getName());
            if(baseMapper.selectCount(Wrappers.lambdaQuery(query)) > 0){
                throw new RuntimeException("商品名称已存在");
            }
        }
        // 对商品编号做唯一约束，没有则使用雪花算法生成，有则判断数据库是否已存在
        System.out.println("-----"+StringUtils.isNotBlank(entity.getProductNumber()));
        if(StringUtils.isNotBlank(entity.getProductNumber())){
            if(baseMapper.selectCount(Wrappers.lambdaQuery()) > 0){
                throw new RuntimeException("商品编号已存在");
            }
        } else {
            // 商品编号未提供，使用雪花算法生成
            long snowflakeId = new SnowflakeGenerator().next();
            entity.setProductNumber(String.valueOf(snowflakeId));
        }

    }

    /**
     * 批量删除商品
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
