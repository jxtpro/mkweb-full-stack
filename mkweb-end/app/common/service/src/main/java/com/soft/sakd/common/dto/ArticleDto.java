package com.soft.sakd.common.dto;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 9:59
 */
public class ArticleDto {
  private Long id;
  private String fileName;
  private String fileDesc;
  private Long browseCount;
  private Long likeCount;
  private String content;
  private List<FileCatoryDto> fileCatory = Lists.newArrayList();
  private List<ItCatoryDto> itCatory = Lists.newArrayList();
  private List<AttachmentsDto> attachments = Lists.newArrayList();
  private Long userId;

  public List<AttachmentsDto> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<AttachmentsDto> attachments) {
    this.attachments = attachments;
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

  public String getFileDesc() {
    return fileDesc;
  }

  public void setFileDesc(String fileDesc) {
    this.fileDesc = fileDesc;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<FileCatoryDto> getFileCatory() {
    return fileCatory;
  }

  public void setFileCatory(List<FileCatoryDto> fileCatory) {
    this.fileCatory = fileCatory;
  }

  public List<ItCatoryDto> getItCatory() {
    return itCatory;
  }

  public void setItCatory(List<ItCatoryDto> itCatory) {
    this.itCatory = itCatory;
  }
}
