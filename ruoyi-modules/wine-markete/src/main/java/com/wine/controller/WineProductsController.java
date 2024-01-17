package com.wine.controller;

import java.util.List;

import com.wine.domain.bo.WineProductsBo;
import com.wine.domain.vo.WineProductsVo;
import com.wine.service.IWineProductsService;
import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;


/**
 * 商品
 *
 * @author Lion Li
 * @date 2024-01-16
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/wine/products")
public class WineProductsController extends BaseController {

    private final IWineProductsService wineProductsService;

    /**
     * 查询商品列表
     */
    @SaCheckPermission("wine:products:list")
    @GetMapping("/list")
    public TableDataInfo<WineProductsVo> list(WineProductsBo bo, PageQuery pageQuery) {
        return wineProductsService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出商品列表
     */
    @SaCheckPermission("wine:products:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(WineProductsBo bo, HttpServletResponse response) {
        List<WineProductsVo> list = wineProductsService.queryList(bo);
        ExcelUtil.exportExcel(list, "商品", WineProductsVo.class, response);
    }

    /**
     * 获取商品详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("wine:products:query")
    @GetMapping("/{id}")
    public R<WineProductsVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(wineProductsService.queryById(id));
    }

    /**
     * 新增商品
     */
    @SaCheckPermission("wine:products:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody WineProductsBo bo) {
        return toAjax(wineProductsService.insertByBo(bo));
    }

    /**
     * 修改商品
     */
    @SaCheckPermission("wine:products:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody WineProductsBo bo) {
        return toAjax(wineProductsService.updateByBo(bo));
    }

    /**
     * 删除商品
     *
     * @param ids 主键串
     */
    @SaCheckPermission("wine:products:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(wineProductsService.deleteWithValidByIds(List.of(ids), true));
    }
}
