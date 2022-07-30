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
import com.guigu.mall.product.domain.SpuComment;
import com.guigu.mall.product.service.ISpuCommentService;
import com.guigu.mall.common.utils.poi.ExcelUtil;
import com.guigu.mall.common.core.page.TableDataInfo;

/**
 * 商品评价Controller
 * 
 * @author longTao
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/product/comment")
public class SpuCommentController extends BaseController
{
    @Autowired
    private ISpuCommentService spuCommentService;

    /**
     * 查询商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('product:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(SpuComment spuComment)
    {
        startPage();
        List<SpuComment> list = spuCommentService.selectSpuCommentList(spuComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('product:comment:export')")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SpuComment spuComment)
    {
        List<SpuComment> list = spuCommentService.selectSpuCommentList(spuComment);
        ExcelUtil<SpuComment> util = new ExcelUtil<SpuComment>(SpuComment.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(spuCommentService.selectSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:add')")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SpuComment spuComment)
    {
        return toAjax(spuCommentService.insertSpuComment(spuComment));
    }

    /**
     * 修改商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:edit')")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SpuComment spuComment)
    {
        return toAjax(spuCommentService.updateSpuComment(spuComment));
    }

    /**
     * 删除商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:remove')")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(spuCommentService.deleteSpuCommentByIds(ids));
    }
}
