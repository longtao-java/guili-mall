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
import com.guigu.mall.product.domain.Attr;
import com.guigu.mall.product.service.IAttrService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * 商品属性Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/attr")
public class AttrController extends BaseController
{
    @Autowired
    private IAttrService attrService;

    /**
     * 查询商品属性列表
     */
    @PreAuthorize("@ss.hasPermi('product:attr:list')")
    @GetMapping("/list")
    public TableDataInfo list(Attr attr)
    {
        startPage();
        List<Attr> list = attrService.selectAttrList(attr);
        return getDataTable(list);
    }

    /**
     * 导出商品属性列表
     */
    @PreAuthorize("@ss.hasPermi('product:attr:export')")
    @Log(title = "商品属性", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Attr attr)
    {
        List<Attr> list = attrService.selectAttrList(attr);
        ExcelUtil<Attr> util = new ExcelUtil<Attr>(Attr.class);
        util.exportExcel(response, list, "商品属性数据");
    }

    /**
     * 获取商品属性详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:attr:query')")
    @GetMapping(value = "/{attrId}")
    public AjaxResult getInfo(@PathVariable("attrId") Long attrId)
    {
        return AjaxResult.success(attrService.selectAttrByAttrId(attrId));
    }

    /**
     * 新增商品属性
     */
    @PreAuthorize("@ss.hasPermi('product:attr:add')")
    @Log(title = "商品属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Attr attr)
    {
        return toAjax(attrService.insertAttr(attr));
    }

    /**
     * 修改商品属性
     */
    @PreAuthorize("@ss.hasPermi('product:attr:edit')")
    @Log(title = "商品属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Attr attr)
    {
        return toAjax(attrService.updateAttr(attr));
    }

    /**
     * 删除商品属性
     */
    @PreAuthorize("@ss.hasPermi('product:attr:remove')")
    @Log(title = "商品属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrIds}")
    public AjaxResult remove(@PathVariable Long[] attrIds)
    {
        return toAjax(attrService.deleteAttrByAttrIds(attrIds));
    }
}
