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
import com.guigu.mall.product.domain.SkuSaleAttrValue;
import com.guigu.mall.product.service.ISkuSaleAttrValueService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * sku销售属性&值Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/value")
public class SkuSaleAttrValueController extends BaseController
{
    @Autowired
    private ISkuSaleAttrValueService skuSaleAttrValueService;

    /**
     * 查询sku销售属性&值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuSaleAttrValue skuSaleAttrValue)
    {
        startPage();
        List<SkuSaleAttrValue> list = skuSaleAttrValueService.selectSkuSaleAttrValueList(skuSaleAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出sku销售属性&值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:export')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuSaleAttrValue skuSaleAttrValue)
    {
        List<SkuSaleAttrValue> list = skuSaleAttrValueService.selectSkuSaleAttrValueList(skuSaleAttrValue);
        ExcelUtil<SkuSaleAttrValue> util = new ExcelUtil<SkuSaleAttrValue>(SkuSaleAttrValue.class);
        util.exportExcel(response, list, "sku销售属性&值数据");
    }

    /**
     * 获取sku销售属性&值详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:value:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuSaleAttrValueService.selectSkuSaleAttrValueById(id));
    }

    /**
     * 新增sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:add')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuSaleAttrValue skuSaleAttrValue)
    {
        return toAjax(skuSaleAttrValueService.insertSkuSaleAttrValue(skuSaleAttrValue));
    }

    /**
     * 修改sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:edit')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuSaleAttrValue skuSaleAttrValue)
    {
        return toAjax(skuSaleAttrValueService.updateSkuSaleAttrValue(skuSaleAttrValue));
    }

    /**
     * 删除sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:remove')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuSaleAttrValueService.deleteSkuSaleAttrValueByIds(ids));
    }
}
