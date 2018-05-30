package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserManagerMapper {
    @Select(" SELECT user_id, user_name, real_name, user_sex, user_birth, user_phone, user_email, user_address, status" +
            " FROM sys_user WHERE user_id = #{userId}")
    UserManagerModel getUserInfo(@Param("userId") Long userId);

    @Update(" UPDATE sys_user SET user_name = #{userName}, real_name = #{realName},user_phone=#{userPhone},user_email=#{userEmail},user_address=#{userAddress}," +
            " user_sex=#{userSex},last_person = #{lastPerson}, last_time = #{lastTime} WHERE user_id = #{userId}")
    int updateUserInfo(UserManagerModel userManagerModel);

    @Update(" UPDATE sys_user SET password = #{newPassword}," +
            " last_person = #{lastPerson}, last_time = now() WHERE user_id = #{userId}")
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword, @Param("lastPerson") Long lastPerson);

    @Insert(" INSERT INTO sys_user (user_name, password, real_name,unit_id,user_sex,user_birth,user_phone,user_email,user_address, add_person, add_time, last_person, last_time)" +
            " VALUES(#{userName}, #{password}, #{realName},0,#{userSex},#{userBirth},#{userPhone},#{userEmail},#{userAddress}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "user_id")
    int insertNewUser(UserManagerModel userManagerModel);

    @Delete(" DELETE FROM sys_user WHERE user_id = #{userId}")
    int deleteUser(@Param("userId") Long userId);

    @Select(" SELECT user_id, user_name, real_name, user_sex, user_birth, user_phone, user_email, user_address, status FROM sys_user" +
            " WHERE ((user_name LIKE CONCAT('%', #{keyword}, '%')) OR (real_name LIKE CONCAT('%', #{keyword}, '%')))")
    List<UserManagerModel> getList(String keyword);

    @Delete(" DELETE FROM sys_user WHERE user_id IN (${userList})")
    int deleteUserList(@Param("userList") String userList);
}
