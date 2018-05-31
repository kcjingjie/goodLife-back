package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.SysDataRoleModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysDataRoleMapper {

    @Select(" SELECT * FROM sys_data_role " +
            " WHERE drole_name LIKE CONCAT('%', #{keyword}, '%')")
    List<SysDataRoleModel> getDataRoleList(String keyword);

    @Insert(" INSERT INTO sys_data_role (drole_name, drole_desc, status,add_person, add_time, last_person, last_time)" +
            " VALUES(#{droleName}, #{droleDesc}, #{status}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "drole_id")
    int insertDataRole(SysDataRoleModel sysDataRoleModel);

    @Delete(" DELETE FROM sys_data_role WHERE drole_id IN (${idList});")
    int deleteDataRole(@Param("idList") String  idList);

    @Select(" SELECT * " +
            " FROM sys_data_role WHERE drole_id = #{id}")
    SysDataRoleModel getDataRoleInfo(@Param("id") Long id);

    @Update(" UPDATE sys_data_role SET drole_name = #{droleName}, drole_desc = #{droleDesc},status=#{status}," +
            " last_person = #{lastPerson}, last_time = #{lastTime} WHERE drole_id = #{droleId}")
    int updateDataRoleInfo(SysDataRoleModel sysDataRoleModel);


}
