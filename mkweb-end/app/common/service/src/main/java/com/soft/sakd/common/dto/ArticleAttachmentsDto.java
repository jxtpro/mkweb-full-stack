package com.soft.sakd.common.dto;

/**
 * @author Administrator
 * @since 2020/4/5 22:35
 */
public class ArticleAttachmentsDto {
  private Long id;

  private Long articleId;

  private Long attachmentId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public Long getAttachmentId() {
    return attachmentId;
  }

  public void setAttachmentId(Long attachmentId) {
    this.attachmentId = attachmentId;
  }
}
