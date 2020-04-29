package com.soft.sakd.biz.vo;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author xujie
 * @since 2020/4/4 17:01
 */
public class UserVo {
  private String userName;
  private String avatar;
  private Long userId;
  private String email;
  private String signature;
  private List<String> auth = Lists.newArrayList();
  private String title;
  private String group;
  private String country;
  private String address;
  private String phone;
  private String password;


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public List<String> getAuth() {
    return auth;
  }

  public void setAuth(List<String> auth) {
    this.auth = auth;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
