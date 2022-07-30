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
import com.guigu.mall.product.domain.AttrGroup;
import com.guigu.mall.product.service.IAttrGroupService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * 属性分组Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/group")
public class AttrGroupController extends BaseController
{
    @Autowired
    private IAttrGroupService attrGroupService;

    /**
     * 查询属性分组列表
     */
    @PreAuthorize("@ss.hasPermi('product:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(AttrGroup attrGroup)
    {
        startPage();
        List<AttrGroup> list = attrGroupService.selectAttrGroupList(attrGroup);
        return getDataTable(list);
    }

    /**
     * 导出属性分组列表
     */
    @PreAuthorize("@ss.hasPermi('product:group:export')")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AttrGroup attrGroup)
    {
        List<AttrGroup> list = attrGroupService.selectAttrGroupList(attrGroup);
        ExcelUtil<AttrGroup> util = new ExcelUtil<AttrGroup>(AttrGroup.class);
        util.exportExcel(response, list, "属性分组数据");
    }

    /**
     * 获取属性分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:group:query')")
    @GetMapping(value = "/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return AjaxResult.success(attrGroupService.selectAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:add')")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.insertAttrGroup(attrGroup));
    }

    /**
     * 修改属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:edit')")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AttrGroup attrGroup)
    {
        return toAjax(attrGroupService.updateAttrGroup(attrGroup));
    }

    /**
     * 删除属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:remove')")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(attrGroupService.deleteAttrGroupByAttrGroupIds(attrGroupIds));
    }
}
