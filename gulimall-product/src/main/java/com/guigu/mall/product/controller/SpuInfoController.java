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
import com.guigu.mall.product.domain.SpuInfo;
import com.guigu.mall.product.service.ISpuInfoService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * spu信息Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/info")
public class SpuInfoController extends BaseController
{
    @Autowired
    private ISpuInfoService spuInfoService;

    /**
     * 查询spu信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuInfo spuInfo)
    {
        startPage();
        List<SpuInfo> list = spuInfoService.selectSpuInfoList(spuInfo);
        return getDataTable(list);
    }

    /**
     * 导出spu信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:export')")
    @Log(title = "spu信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuInfo spuInfo)
    {
        List<SpuInfo> list = spuInfoService.selectSpuInfoList(spuInfo);
        ExcelUtil<SpuInfo> util = new ExcelUtil<SpuInfo>(SpuInfo.class);
        util.exportExcel(response, list, "spu信息数据");
    }

    /**
     * 获取spu信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuInfoService.selectSpuInfoById(id));
    }

    /**
     * 新增spu信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:add')")
    @Log(title = "spu信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuInfo spuInfo)
    {
        return toAjax(spuInfoService.insertSpuInfo(spuInfo));
    }

    /**
     * 修改spu信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:edit')")
    @Log(title = "spu信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuInfo spuInfo)
    {
        return toAjax(spuInfoService.updateSpuInfo(spuInfo));
    }

    /**
     * 删除spu信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:remove')")
    @Log(title = "spu信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuInfoService.deleteSpuInfoByIds(ids));
    }
}
