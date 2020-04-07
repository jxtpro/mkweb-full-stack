package com.soft.sakd.core.model.entity;

import java.io.Serializable;

public class ArticleItCatory implements Serializable {
    private Long articleId;

    private Long itCatoryId;

    private static final long serialVersionUID = 1L;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getItCatoryId() {
        return itCatoryId;
    }

    public void setItCatoryId(Long itCatoryId) {
        this.itCatoryId = itCatoryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", itCatoryId=").append(itCatoryId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}