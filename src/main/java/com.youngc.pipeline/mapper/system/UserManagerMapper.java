package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.UserUserManagerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserManagerMapper {
    @Select(" SELECT id, user_name, real_name, user_sex, user_birth, user_phone, user_email, user_address, status" +
            " FROM sys_user WHERE id = #{userId}")
    UserUserManagerModel getUserInfo(@Param("userId") Long userId);

    @Update(" UPDATE sys_user SET user_name = #{userName}, real_name = #{realName},user_phone=#{userPhone},user_email=#{userEmail},user_address=#{userAddress}," +
            " user_sex=#{userSex},last_person = #{lastPerson}, last_time = #{lastTime} WHERE id = #{id}")
    int updateUserInfo(UserUserManagerModel userUserManagerModel);

    @Update(" UPDATE sys_user SET password = #{newPassword}," +
            " last_person = #{lastPerson}, last_time = now() WHERE id = #{userId}")
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword, @Param("lastPerson") Long lastPerson);

    @Insert(" INSERT INTO sys_user (user_name, password, real_name,unit_id,user_sex,user_birth,user_phone,user_email,user_address, add_person, add_time, last_person, last_time)" +
            " VALUES(#{userName}, #{password}, #{realName},0,#{userSex},#{userBirth},#{userPhone},#{userEmail},#{userAddress}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewUser(UserUserManagerModel userUserManagerModel);

    @Delete(" DELETE FROM sys_user WHERE id = #{userId}")
    int deleteUser(@Param("userId") Long userId);

    @Select(" SELECT id, user_name, real_name, user_sex, user_birth, user_phone, user_email, user_address, status FROM sys_user" +
            " WHERE ((user_name LIKE CONCAT('%', #{keyword}, '%')) OR (real_name LIKE CONCAT('%', #{keyword}, '%')))")
    List<UserUserManagerModel> getList(String keyword);

    @Delete(" DELETE FROM sys_user WHERE id IN (${userList})")
    int deleteUserList(@Param("userList") String userList);
}
