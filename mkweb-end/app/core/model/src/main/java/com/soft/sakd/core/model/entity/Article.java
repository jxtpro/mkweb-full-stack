package com.soft.sakd.core.model.entity;

import java.io.Serializable;

public class Article implements Serializable {
  private Long id;

  private String fileDesc;

  private String fileName;

  private Long userId;

  private Long browseCount;

  private Long likeCount;

  private String content;

  private static final long serialVersionUID = 1L;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileDesc() {
    return fileDesc;
  }

  public void setFileDesc(String fileDesc) {
    this.fileDesc = fileDesc == null ? null : fileDesc.trim();
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName == null ? null : fileName.trim();
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getBrowseCount() {
    return browseCount;
  }

  public void setBrowseCount(Long browseCount) {
    this.browseCount = browseCount;
  }

  public Long getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Long likeCount) {
    this.likeCount = likeCount;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", fileDesc=").append(fileDesc);
    sb.append(", fileName=").append(fileName);
    sb.append(", userId=").append(userId);
    sb.append(", browseCount=").append(browseCount);
    sb.append(", likeCount=").append(likeCount);
    sb.append(", content=").append(content);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
