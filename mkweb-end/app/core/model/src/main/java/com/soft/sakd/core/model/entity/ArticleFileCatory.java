package com.soft.sakd.core.model.entity;

import java.io.Serializable;

public class ArticleFileCatory implements Serializable {
    private Long articleId;

    private Long fileCatoryId;

    private static final long serialVersionUID = 1L;

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getFileCatoryId() {
        return fileCatoryId;
    }

    public void setFileCatoryId(Long fileCatoryId) {
        this.fileCatoryId = fileCatoryId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", fileCatoryId=").append(fileCatoryId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}