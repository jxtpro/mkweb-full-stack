package com.soft.sakd.common.facade;

import com.soft.sakd.common.dto.UserDto;

/**
 * @author xujie
 * @since 2020/4/2 10:26
 */
public interface UserService {

  UserDto findUserByNameAndPassWord(String name, String password);
}
