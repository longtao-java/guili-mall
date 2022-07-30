package com.guigu.mall.product.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guigu.mall.common.annotation.Log;
import com.guigu.mall.common.core.controller.BaseController;
import com.guigu.mall.common.core.domain.AjaxResult;
import com.guigu.mall.common.enums.BusinessType;
import com.guigu.mall.product.domain.SpuInfoDesc;
import com.guigu.mall.product.service.ISpuInfoDescService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * spu信息介绍Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/desc")
public class SpuInfoDescController extends BaseController
{
    @Autowired
    private ISpuInfoDescService spuInfoDescService;

    /**
     * 查询spu信息介绍列表
     */
    @PreAuthorize("@ss.hasPermi('product:desc:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuInfoDesc spuInfoDesc)
    {
        startPage();
        List<SpuInfoDesc> list = spuInfoDescService.selectSpuInfoDescList(spuInfoDesc);
        return getDataTable(list);
    }

    /**
     * 导出spu信息介绍列表
     */
    @PreAuthorize("@ss.hasPermi('product:desc:export')")
    @Log(title = "spu信息介绍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuInfoDesc spuInfoDesc)
    {
        List<SpuInfoDesc> list = spuInfoDescService.selectSpuInfoDescList(spuInfoDesc);
        ExcelUtil<SpuInfoDesc> util = new ExcelUtil<SpuInfoDesc>(SpuInfoDesc.class);
        util.exportExcel(response, list, "spu信息介绍数据");
    }

    /**
     * 获取spu信息介绍详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:desc:query')")
    @GetMapping(value = "/{spuId}")
    public AjaxResult getInfo(@PathVariable("spuId") Long spuId)
    {
        return AjaxResult.success(spuInfoDescService.selectSpuInfoDescBySpuId(spuId));
    }

    /**
     * 新增spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:add')")
    @Log(title = "spu信息介绍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuInfoDesc spuInfoDesc)
    {
        return toAjax(spuInfoDescService.insertSpuInfoDesc(spuInfoDesc));
    }

    /**
     * 修改spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:edit')")
    @Log(title = "spu信息介绍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuInfoDesc spuInfoDesc)
    {
        return toAjax(spuInfoDescService.updateSpuInfoDesc(spuInfoDesc));
    }

    /**
     * 删除spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:remove')")
    @Log(title = "spu信息介绍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{spuIds}")
    public AjaxResult remove(@PathVariable Long[] spuIds)
    {
        return toAjax(spuInfoDescService.deleteSpuInfoDescBySpuIds(spuIds));
    }
}
