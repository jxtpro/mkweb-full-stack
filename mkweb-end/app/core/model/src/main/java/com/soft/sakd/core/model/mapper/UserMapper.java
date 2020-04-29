package com.soft.sakd.core.model.mapper;

import com.soft.sakd.core.model.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User findUserByNameAndPassWord(@Param("name") String name, @Param("password") String password);
}