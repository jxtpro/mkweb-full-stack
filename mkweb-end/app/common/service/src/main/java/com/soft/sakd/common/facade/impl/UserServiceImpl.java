package com.soft.sakd.common.facade.impl;

import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.convert.impl.UserSrvConvert;
import com.soft.sakd.common.dto.UserDto;
import com.soft.sakd.common.facade.UserService;
import com.soft.sakd.core.model.entity.User;
import com.soft.sakd.core.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sun.security.provider.MD5;
import sun.security.rsa.RSASignature.MD5withRSA;

/**
 * @author xujie
 * @since 2020/4/2 10:28
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDto findUserByNameAndPassWord(String name, String password) {
    Assert.notNull(name,"用户名不为空");
    Assert.notNull(password,"密码不为空");
    Assert.hasLength(name,"用户名不为空");
    Assert.hasLength(password,"密码不为空");
    Assert.hasText(name,"用户名不为空");
    Assert.hasText(password,"密码不为空");
    Assert.isTrue(name.length() >= 2 && name.length() < 50,"用户名长度不匹配，范围{2，50}");
    Assert.isTrue(password.length() > 3 && password.length() < 128,"密码长度不匹配,范围{3，16}");
    UserSrvConvert userSrvConvert = new UserSrvConvert();
    User user = userMapper.findUserByNameAndPassWord(name, password);
    return userSrvConvert.toDto(user);
  }
}
