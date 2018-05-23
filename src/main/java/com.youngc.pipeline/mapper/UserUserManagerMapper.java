package com.youngc.pipeline.mapper;

import com.youngc.pipeline.model.UserUserManagerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserUserManagerMapper {
    @Select(" SELECT id, username, realname" +
            " FROM auth_user WHERE id = #{userId}")
    UserUserManagerModel getUserInfo(@Param("userId") Long userId);

    @Update(" UPDATE auth_user SET username = #{username}, realname = #{realname}," +
            " last_person = #{lastPerson}, last_time = #{lastTime} WHERE id = #{id}")
    int updateUserInfo(UserUserManagerModel userUserManagerModel);

    @Update(" UPDATE auth_user SET password = #{newPassword}," +
            " last_person = #{lastPerson}, last_time = now() WHERE id = #{userId}")
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword, @Param("lastPerson") Long lastPerson);

    @Insert(" INSERT INTO auth_user (username, password, realname, add_person, add_time, last_person, last_time)" +
            " VALUES(#{username}, #{password}, #{realname}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewUser(UserUserManagerModel userUserManagerModel);

    @Delete(" DELETE FROM auth_user WHERE id = #{userId}")
    int deleteUser(@Param("userId") Long userId);

    @Select(" SELECT id, username, realname FROM auth_user" +
            " WHERE ((username LIKE CONCAT('%', #{keyword}, '%')) OR (realname LIKE CONCAT('%', #{keyword}, '%')))")
    List<UserUserManagerModel> getList(String keyword);

    @Delete(" DELETE FROM auth_user WHERE id IN (${userList})")
    int deleteUserList(@Param("userList") String userList);
}
