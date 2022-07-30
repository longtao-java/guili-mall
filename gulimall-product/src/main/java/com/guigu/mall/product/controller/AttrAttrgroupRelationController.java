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
import com.guigu.mall.product.domain.AttrAttrgroupRelation;
import com.guigu.mall.product.service.IAttrAttrgroupRelationService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * 属性&属性分组关联Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/relation")
public class AttrAttrgroupRelationController extends BaseController
{
    @Autowired
    private IAttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 查询属性&属性分组关联列表
     */
    @PreAuthorize("@ss.hasPermi('product:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttrAttrgroupRelation attrAttrgroupRelation)
    {
        startPage();
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.selectAttrAttrgroupRelationList(attrAttrgroupRelation);
        return getDataTable(list);
    }

    /**
     * 导出属性&属性分组关联列表
     */
    @PreAuthorize("@ss.hasPermi('product:relation:export')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrAttrgroupRelation attrAttrgroupRelation)
    {
        List<AttrAttrgroupRelation> list = attrAttrgroupRelationService.selectAttrAttrgroupRelationList(attrAttrgroupRelation);
        ExcelUtil<AttrAttrgroupRelation> util = new ExcelUtil<AttrAttrgroupRelation>(AttrAttrgroupRelation.class);
        util.exportExcel(response, list, "属性&属性分组关联数据");
    }

    /**
     * 获取属性&属性分组关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(attrAttrgroupRelationService.selectAttrAttrgroupRelationById(id));
    }

    /**
     * 新增属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('product:relation:add')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return toAjax(attrAttrgroupRelationService.insertAttrAttrgroupRelation(attrAttrgroupRelation));
    }

    /**
     * 修改属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('product:relation:edit')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttrAttrgroupRelation attrAttrgroupRelation)
    {
        return toAjax(attrAttrgroupRelationService.updateAttrAttrgroupRelation(attrAttrgroupRelation));
    }

    /**
     * 删除属性&属性分组关联
     */
    @PreAuthorize("@ss.hasPermi('product:relation:remove')")
    @Log(title = "属性&属性分组关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(attrAttrgroupRelationService.deleteAttrAttrgroupRelationByIds(ids));
    }
}
