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
import com.guigu.mall.product.domain.SkuInfo;
import com.guigu.mall.product.service.ISkuInfoService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * sku信息Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/info")
public class SkuInfoController extends BaseController
{
    @Autowired
    private ISkuInfoService skuInfoService;

    /**
     * 查询sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuInfo skuInfo)
    {
        startPage();
        List<SkuInfo> list = skuInfoService.selectSkuInfoList(skuInfo);
        return getDataTable(list);
    }

    /**
     * 导出sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:export')")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuInfo skuInfo)
    {
        List<SkuInfo> list = skuInfoService.selectSkuInfoList(skuInfo);
        ExcelUtil<SkuInfo> util = new ExcelUtil<SkuInfo>(SkuInfo.class);
        util.exportExcel(response, list, "sku信息数据");
    }

    /**
     * 获取sku信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:query')")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return AjaxResult.success(skuInfoService.selectSkuInfoBySkuId(skuId));
    }

    /**
     * 新增sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:add')")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuInfo skuInfo)
    {
        return toAjax(skuInfoService.insertSkuInfo(skuInfo));
    }

    /**
     * 修改sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:edit')")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuInfo skuInfo)
    {
        return toAjax(skuInfoService.updateSkuInfo(skuInfo));
    }

    /**
     * 删除sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:remove')")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(skuInfoService.deleteSkuInfoBySkuIds(skuIds));
    }
}
