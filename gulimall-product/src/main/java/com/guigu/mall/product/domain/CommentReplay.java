package com.guigu.mall.product.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guigu.mall.common.annotation.Excel;
import com.guigu.mall.common.core.domain.BaseEntity;

/**
 * 商品评价回复关系对象 pms_comment_replay
 * 
 * @author longTao
 * @date 2022-07-30
 */
public class CommentReplay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 评论id */
    @Excel(name = "评论id")
    private Long commentId;

    /** 回复id */
    @Excel(name = "回复id")
    private Long replyId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setReplyId(Long replyId) 
    {
        this.replyId = replyId;
    }

    public Long getReplyId() 
    {
        return replyId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("commentId", getCommentId())
            .append("replyId", getReplyId())
            .toString();
    }
}
