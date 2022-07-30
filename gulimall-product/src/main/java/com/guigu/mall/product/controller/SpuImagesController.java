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
import com.guigu.mall.product.domain.SpuImages;
import com.guigu.mall.product.service.ISpuImagesService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * spu图片Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/images")
public class SpuImagesController extends BaseController
{
    @Autowired
    private ISpuImagesService spuImagesService;

    /**
     * 查询spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuImages spuImages)
    {
        startPage();
        List<SpuImages> list = spuImagesService.selectSpuImagesList(spuImages);
        return getDataTable(list);
    }

    /**
     * 导出spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:export')")
    @Log(title = "spu图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuImages spuImages)
    {
        List<SpuImages> list = spuImagesService.selectSpuImagesList(spuImages);
        ExcelUtil<SpuImages> util = new ExcelUtil<SpuImages>(SpuImages.class);
        util.exportExcel(response, list, "spu图片数据");
    }

    /**
     * 获取spu图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuImagesService.selectSpuImagesById(id));
    }

    /**
     * 新增spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:add')")
    @Log(title = "spu图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuImages spuImages)
    {
        return toAjax(spuImagesService.insertSpuImages(spuImages));
    }

    /**
     * 修改spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:edit')")
    @Log(title = "spu图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuImages spuImages)
    {
        return toAjax(spuImagesService.updateSpuImages(spuImages));
    }

    /**
     * 删除spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:remove')")
    @Log(title = "spu图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuImagesService.deleteSpuImagesByIds(ids));
    }
}
