package com.youngc.pipeline.mapper.login;

import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author liweiqiang
 */
@Component
public interface AuthUserMapper {

    @Select(" SELECT id, user_name, password, real_name FROM sys_user WHERE user_name = #{username}")
    UserManagerModel getUserByUsername(@Param("username") String username);

    @Select(" SELECT password FROM sys_user" +
            " WHERE user_name = #{username}")
    String getHashedPasswd(@Param("userId") Long userId);

    @Update(" UPDATE sys_user SET password = #{hashedNewPasswd}" +
            " WHERE user_name = #{username}")
    int updateUserPassword(@Param("userId") Long userId,
                           @Param("hashedNewPasswd") String hashedNewPasswd);
}
