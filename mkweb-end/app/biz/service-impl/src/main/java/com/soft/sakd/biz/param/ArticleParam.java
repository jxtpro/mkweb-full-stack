package com.soft.sakd.biz.param;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/2 9:59
 */
public class ArticleParam {
  private Long id;
  private String fileName;
  private String fileDesc;
  private String content;
  private List<Long> fileCatory = Lists.newArrayList();
  private List<Long> itCatory = Lists.newArrayList();
  private Long userId;
  private List<AttachmentsParam> attachments = Lists.newArrayList();

  public List<AttachmentsParam> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<AttachmentsParam> attachments) {
    this.attachments = attachments;
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

  public List<Long> getFileCatory() {
    return fileCatory;
  }

  public void setFileCatory(List<Long> fileCatory) {
    this.fileCatory = fileCatory;
  }

  public List<Long> getItCatory() {
    return itCatory;
  }

  public void setItCatory(List<Long> itCatory) {
    this.itCatory = itCatory;
  }
}
