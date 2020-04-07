package com.soft.sakd.biz.vo;

/**
 * @author xujie
 * @since 2020/4/5 15:55
 */
public class UploadResultVo {
  private String msg;
  private Object data;
  private boolean status;

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
