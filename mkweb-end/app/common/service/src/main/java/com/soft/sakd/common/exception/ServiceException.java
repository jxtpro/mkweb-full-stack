package com.soft.sakd.common.exception;

/**
 * @author xujie
 * @since 2020/4/2 21:05
 */
public class ServiceException extends Exception {

  public ServiceException(String msg, Exception e) {
    super(msg, e);
  }

  public ServiceException(String msg) {
    super(msg);
  }
}
