package com.soft.sakd.common.convert.impl;

import com.google.common.collect.Lists;
import com.soft.sakd.common.convert.SrvConvert;
import com.soft.sakd.common.dto.UserDto;
import com.soft.sakd.core.model.entity.User;
import java.util.List;

/**
 * @author Administrator
 * @since 2020/4/10 20:46
 */
public class UserSrvConvert implements SrvConvert<UserDto, User> {

  @Override
  public User toDao(UserDto userDto) {
    User user = new User();
    user.setName(userDto.getName());
    user.setPassword(userDto.getPassword());
    user.setId(userDto.getId());
    user.setAuth(userDto.getAuth());
    return user;
  }

  @Override
  public UserDto toDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setPassword(user.getPassword());
    userDto.setName(user.getName());
    userDto.setId(user.getId());
    userDto.setAuth(user.getAuth());
    return userDto;
  }

  @Override
  public List<UserDto> toListDto(List<User> userList) {
    List<UserDto> userDtoList = Lists.newArrayList();
    userList.forEach(user -> {
      userDtoList.add(toDto(user));
    });
    return userDtoList;
  }

  @Override
  public List<User> toListDao(List<UserDto> userDtoList) {
    List<User> userList = Lists.newArrayList();
    userDtoList.forEach(userDto -> {
      userList.add(toDao(userDto));
    });
    return userList;
  }
}
