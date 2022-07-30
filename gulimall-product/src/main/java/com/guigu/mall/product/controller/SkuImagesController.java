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
import com.guigu.mall.product.domain.SkuImages;
import com.guigu.mall.product.service.ISkuImagesService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * sku图片Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/images")
public class SkuImagesController extends BaseController
{
    @Autowired
    private ISkuImagesService skuImagesService;

    /**
     * 查询sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuImages skuImages)
    {
        startPage();
        List<SkuImages> list = skuImagesService.selectSkuImagesList(skuImages);
        return getDataTable(list);
    }

    /**
     * 导出sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:export')")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuImages skuImages)
    {
        List<SkuImages> list = skuImagesService.selectSkuImagesList(skuImages);
        ExcelUtil<SkuImages> util = new ExcelUtil<SkuImages>(SkuImages.class);
        util.exportExcel(response, list, "sku图片数据");
    }

    /**
     * 获取sku图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(skuImagesService.selectSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:add')")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuImages skuImages)
    {
        return toAjax(skuImagesService.insertSkuImages(skuImages));
    }

    /**
     * 修改sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:edit')")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuImages skuImages)
    {
        return toAjax(skuImagesService.updateSkuImages(skuImages));
    }

    /**
     * 删除sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:remove')")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuImagesService.deleteSkuImagesByIds(ids));
    }
}
