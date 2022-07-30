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
import com.guigu.mall.product.domain.Category;
import com.guigu.mall.product.service.ICategoryService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * 商品三级分类Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController extends BaseController
{
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询商品三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('product:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category)
    {
        startPage();
        List<Category> list = categoryService.selectCategoryList(category);
        return getDataTable(list);
    }

    /**
     * 导出商品三级分类列表
     */
    @PreAuthorize("@ss.hasPermi('product:category:export')")
    @Log(title = "商品三级分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Category category)
    {
        List<Category> list = categoryService.selectCategoryList(category);
        ExcelUtil<Category> util = new ExcelUtil<Category>(Category.class);
        util.exportExcel(response, list, "商品三级分类数据");
    }

    /**
     * 获取商品三级分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:category:query')")
    @GetMapping(value = "/{catId}")
    public AjaxResult getInfo(@PathVariable("catId") Long catId)
    {
        return AjaxResult.success(categoryService.selectCategoryByCatId(catId));
    }

    /**
     * 新增商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('product:category:add')")
    @Log(title = "商品三级分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Category category)
    {
        return toAjax(categoryService.insertCategory(category));
    }

    /**
     * 修改商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('product:category:edit')")
    @Log(title = "商品三级分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Category category)
    {
        return toAjax(categoryService.updateCategory(category));
    }

    /**
     * 删除商品三级分类
     */
    @PreAuthorize("@ss.hasPermi('product:category:remove')")
    @Log(title = "商品三级分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catIds}")
    public AjaxResult remove(@PathVariable Long[] catIds)
    {
        return toAjax(categoryService.deleteCategoryByCatIds(catIds));
    }
}
