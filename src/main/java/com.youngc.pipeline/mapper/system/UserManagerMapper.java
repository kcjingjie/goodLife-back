package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserManagerMapper {
    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @Select(" SELECT user_id, user_name, real_name, user_sex, user_phone, user_email, user_address, status" +
            " FROM sys_user WHERE user_id = #{userId}")
    UserManagerModel getUserInfo(@Param("userId") Long userId);

    /**
     * 修改用户信息
     * @param userManagerModel
     * @return
     */
    @Update(" UPDATE sys_user SET user_name = #{userName}, real_name = #{realName},user_phone=#{userPhone},user_email=#{userEmail},user_address=#{userAddress}," +
            " user_sex=#{userSex},last_person = #{lastPerson}, last_time = now() WHERE user_id = #{userId}")
    int updateUserInfo(UserManagerModel userManagerModel);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @param lastPerson
     * @return
     */
    @Update(" UPDATE sys_user SET password = #{newPassword}," +
            " last_person = #{lastPerson}, last_time = now() WHERE user_id = #{userId}")
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword, @Param("lastPerson") Long lastPerson);

    /**
     * 添加用户
     * @param userManagerModel
     * @return
     */
    @Insert(" INSERT INTO sys_user (user_name, password, real_name,unit_id,user_sex,user_phone,user_email,user_address, add_person, add_time, last_person, last_time)" +
            " VALUES(#{userName}, #{password}, #{realName},0,#{userSex},#{userPhone},#{userEmail},#{userAddress}, #{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "user_id")
    int insertNewUser(UserManagerModel userManagerModel);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @Delete(" DELETE FROM sys_user WHERE user_id = #{userId}")
    int deleteUser(@Param("userId") Long userId);

    /**
     * 分页获取用户信息
     * @param keyword
     * @return
     */
    @Select(" SELECT user_id, user_name, real_name, user_sex, user_phone, user_email, user_address, status FROM sys_user" +
            " WHERE ((user_name LIKE CONCAT('%', #{keyword}, '%')) OR (real_name LIKE CONCAT('%', #{keyword}, '%')))")
    List<UserManagerModel> getList(String keyword);

    /**
     * 批量删除用户
     * @param userList
     * @return
     */
    @Delete(" DELETE FROM sys_user WHERE user_id IN (${userList})")
    int deleteUserList(@Param("userList") String userList);

    /**
     * 查询单位表的内容
     */
    @Select("SELECT unit_id, unit_name FROM sys_unit")
    List<UnitModel> getUnitList();
}
