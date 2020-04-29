package com.soft.sakd.biz.mange.impl;

import com.soft.sakd.biz.convert.impl.UserBizConvert;
import com.soft.sakd.biz.mange.LoginMange;
import com.soft.sakd.biz.param.UserParam;
import com.soft.sakd.biz.vo.UserVo;
import com.soft.sakd.common.dto.UserDto;
import com.soft.sakd.common.facade.UserService;
import java.nio.charset.Charset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

/**
 * @author xujie
 * @since 2020/4/10 20:29
 * 注册时密码加密
 * String md5Password = DigestUtils.md5DigestAsHex(passwordString.getBytes());
 * 登录时密码校验
 * DigestUtils.md5DigestAsHex(newPassword.getBytes()).equals(oldPasswordString)
 * 如果密码字符中存在中文，则一定要指定编码类型
 * DigestUtils.md5DigestAsHex(str.getBytes(Charset.forName("UTF-8")));
 */
@Service
public class LoginMangeImpl implements LoginMange {

  @Autowired
  private UserService userService;

  @Override
  public UserVo currentUser() {
    return null;
  }

  @Override
  public UserVo login(UserParam userParam) {
    Assert.notNull(userParam,"登录信息不为空");
    String name = userParam.getUserName();
    String password = userParam.getPassword();
    Assert.notNull(name,"用户名不为空");
    Assert.notNull(password,"密码不为空");
    Assert.hasLength(name,"用户名不为空");
    Assert.hasLength(password,"密码不为空");
    Assert.hasText(name,"用户名不为空");
    Assert.hasText(password,"密码不为空");
    Assert.isTrue(name.length() >= 2 && name.length() < 50,"用户名长度不匹配，范围{2，50}");
    Assert.isTrue(password.length() > 3 && password.length() < 16,"密码长度不匹配,范围{3，16}");

    UserDto userDto = userService.findUserByNameAndPassWord(userParam.getUserName(), DigestUtils.md5DigestAsHex(password.getBytes(Charset.forName("UTF-8"))));
    UserBizConvert userBizConvert = new UserBizConvert();
    return userBizConvert.dtoToVo(userDto);
  }
}
