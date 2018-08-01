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

    @Select(" SELECT su.user_id, su.user_name, su.password, su.real_name,"+
            " GROUP_CONCAT(DISTINCT sur.role_id SEPARATOR ',')role_ids,GROUP_CONCAT(DISTINCT sudr.drole_id SEPARATOR ',')drole_ids"+
            " FROM sys_user su LEFT JOIN sys_user_role sur on sur.user_id=su.user_id " +
            " LEFT JOIN sys_user_data_role sudr on sudr.user_id=su.user_id"+
            " WHERE su.user_name = #{username}")
    UserManagerModel getUserByUsername(@Param("username") String username);

    @Select(" SELECT password FROM sys_user" +
            " WHERE user_name = #{username}")
    String getHashedPasswd(@Param("userId") Long userId);

    @Update(" UPDATE sys_user SET password = #{hashedNewPasswd},last_person = #{userId},last_time = now()" +
            " WHERE user_id = #{userId}")
    int updateUserPassword(@Param("userId") Long userId,
                           @Param("hashedNewPasswd") String hashedNewPasswd);
}
