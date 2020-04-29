package com.soft.sakd.biz.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.biz.convert.BizConvert;
import com.soft.sakd.biz.param.UserParam;
import com.soft.sakd.biz.vo.UserVo;
import com.soft.sakd.common.dto.UserDto;
import java.util.Arrays;
import org.springframework.util.StringUtils;

/**
 * @author Administrator
 * @since 2020/4/10 20:33
 */
public class UserBizConvert implements BizConvert<UserVo, UserParam, UserDto> {


  @Override
  public UserDto paramToDto(UserParam userParam) {
    UserDto userDto = new UserDto();
    userDto.setName(userParam.getUserName());
    userDto.setPassword(userParam.getPassword());
    return userDto;
  }

  @Override
  public UserVo dtoToVo(UserDto userDto) {
    UserVo userVo = new UserVo();
    userVo.setUserName(userDto.getName());
    String auth = userDto.getAuth();
    if (!StringUtils.isEmpty(auth)) {
      userVo.setAuth(Arrays.asList(StringUtils.delimitedListToStringArray(auth,",")));
    } else {
      userVo.setAuth(Lists.newArrayList());
    }
    return userVo;
  }
}
