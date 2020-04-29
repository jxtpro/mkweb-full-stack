package com.soft.sakd.common.dto;

/**
 * @author Administrator
 * @since 2020/4/10 20:35
 */
public class UserDto {

  private Long id;

  private String name;

  private String password;

  private String auth;


  public String getAuth() {
    return auth;
  }

  public void setAuth(String auth) {
    this.auth = auth;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
