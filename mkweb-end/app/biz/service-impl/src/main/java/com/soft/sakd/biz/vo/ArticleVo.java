package com.soft.sakd.biz.vo;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 9:59
 */
public class ArticleVo {
  private String fileName;
  private String fileDesc;
  private Long UserId;
  private Long id;
  private String content;
  private List<FileCatoryVo> fileCatory = Lists.newArrayList();
  private List<ItCatoryVo> itCatory = Lists.newArrayList();
  private List<AttachmentsVo> attachments = Lists.newArrayList();

  public List<AttachmentsVo> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<AttachmentsVo> attachments) {
    this.attachments = attachments;
  }

  public List<FileCatoryVo> getFileCatory() {
    return fileCatory;
  }

  public String getFileDesc() {
    return fileDesc;
  }

  public void setFileDesc(String fileDesc) {
    this.fileDesc = fileDesc;
  }

  public void setFileCatory(List<FileCatoryVo> fileCatory) {
    this.fileCatory = fileCatory;
  }

  public List<ItCatoryVo> getItCatory() {
    return itCatory;
  }

  public void setItCatory(List<ItCatoryVo> itCatory) {
    this.itCatory = itCatory;
  }

  public Long getUserId() {
    return UserId;
  }

  public void setUserId(Long userId) {
    UserId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
