package com.guigu.mall.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guigu.mall.product.mapper.CategoryMapper;
import com.guigu.mall.product.domain.Category;
import com.guigu.mall.product.service.ICategoryService;

/**
 * 商品三级分类Service业务层处理
 * 
 * @author longTao
 * @date 2022-07-30
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询商品三级分类
     * 
     * @param catId 商品三级分类主键
     * @return 商品三级分类
     */
    @Override
    public Category selectCategoryByCatId(Long catId)
    {
        return categoryMapper.selectCategoryByCatId(catId);
    }

    /**
     * 查询商品三级分类列表
     * 
     * @param category 商品三级分类
     * @return 商品三级分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改商品三级分类
     * 
     * @param category 商品三级分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除商品三级分类
     * 
     * @param catIds 需要删除的商品三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatIds(Long[] catIds)
    {
        return categoryMapper.deleteCategoryByCatIds(catIds);
    }

    /**
     * 删除商品三级分类信息
     * 
     * @param catId 商品三级分类主键
     * @return 结果
     */
    @Override
    public int deleteCategoryByCatId(Long catId)
    {
        return categoryMapper.deleteCategoryByCatId(catId);
    }
}
