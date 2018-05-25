package com.youngc.pipeline.mapper.login;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * @author liweiqiang
 */
@Component
public interface AuthTokenMapper {
    @Select(" SELECT user_id FROM sys_token WHERE token = #{token}")
    Long getUserIdByToken(@Param("token") String token);

    @Select(" SELECT COUNT(user_id) FROM sys_token WHERE token = #{token}")
    Integer isTokenExists(@Param("token") String token);

    @Select(" SELECT COUNT(user_id) FROM sys_token WHERE user_id = #{userId}")
    Integer isTokenExistsById(@Param("userId") Long userId);

    @Insert(" INSERT INTO sys_token (user_id, token) VALUES (#{id}, #{token})")
    int insertNewToken(@Param("id") Long userId, @Param("token") String token);

    @Update(" UPDATE sys_token SET token = #{newToken}" +
            " WHERE user_id = #{userId}")
    int updateToken(@Param("userId") Long userId, @Param("newToken") String newToken);

    @Delete(" DELETE FROM sys_token WHERE token = #{token}")
    int delete(@Param("token") String token);
}
