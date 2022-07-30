package com.guigu.mall.product.mapper;

import java.util.List;
import com.guigu.mall.product.domain.CommentReplay;

/**
 * 商品评价回复关系Mapper接口
 * 
 * @author longTao
 * @date 2022-07-30
 */
public interface CommentReplayMapper 
{
    /**
     * 查询商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 商品评价回复关系
     */
    public CommentReplay selectCommentReplayById(Long id);

    /**
     * 查询商品评价回复关系列表
     * 
     * @param commentReplay 商品评价回复关系
     * @return 商品评价回复关系集合
     */
    public List<CommentReplay> selectCommentReplayList(CommentReplay commentReplay);

    /**
     * 新增商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    public int insertCommentReplay(CommentReplay commentReplay);

    /**
     * 修改商品评价回复关系
     * 
     * @param commentReplay 商品评价回复关系
     * @return 结果
     */
    public int updateCommentReplay(CommentReplay commentReplay);

    /**
     * 删除商品评价回复关系
     * 
     * @param id 商品评价回复关系主键
     * @return 结果
     */
    public int deleteCommentReplayById(Long id);

    /**
     * 批量删除商品评价回复关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommentReplayByIds(Long[] ids);
}
