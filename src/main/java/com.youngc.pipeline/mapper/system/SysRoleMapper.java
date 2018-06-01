package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.SysDataRoleModel;
import com.youngc.pipeline.model.SysRoleModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysRoleMapper {

    @Select(" SELECT * FROM sys_role " +
            " WHERE role_name LIKE CONCAT('%', #{keyword}, '%')")
    List<SysRoleModel> getRoleList(String keyword);

    @Insert(" INSERT INTO sys_role (role_name, role_desc, status,add_person, add_time, last_person, last_time)" +
            " VALUES(#{roleName}, #{roleDesc}, #{status}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "role_id")
    int insertRole(SysRoleModel sysRoleModel);

    @Delete(" DELETE FROM sys_role WHERE role_id IN (${idList});")
    int deleteRole(@Param("idList") String  idList);

    @Select(" SELECT * " +
            " FROM sys_role WHERE role_id = #{id}")
    SysRoleModel getRoleInfo(@Param("id") Long id);

    @Update(" UPDATE sys_role SET role_name = #{roleName}, role_desc = #{roleDesc},status=#{status}," +
            " last_person = #{lastPerson}, last_time = #{lastTime} WHERE role_id = #{roleId}")
    int updateRoleInfo(SysRoleModel sysRoleModel);
}
