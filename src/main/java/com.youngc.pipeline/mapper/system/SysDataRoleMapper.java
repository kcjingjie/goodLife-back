package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.SysDataRoleModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysDataRoleMapper {

    /**
     * 获取数据角色信息
     * @param keyword
     * @return
     */
    @Select(" SELECT drole_id,drole_name,drole_desc,status FROM sys_data_role " +
            " WHERE drole_name LIKE CONCAT('%', #{keyword}, '%')")
    List<SysDataRoleModel> getDataRoleList(String keyword);

    /**
     * 添加数据角色信息
     * @param sysDataRoleModel
     * @return
     */
    @Insert(" INSERT INTO sys_data_role (drole_name, drole_desc, status,add_person, add_time, last_person, last_time)" +
            " VALUES(#{droleName}, #{droleDesc}, #{status}, #{addPerson}, now(), #{lastPerson},now())")
    @Options(useGeneratedKeys = true, keyColumn = "drole_id")
    int insertDataRole(SysDataRoleModel sysDataRoleModel);

    /**
     * 删除数据角色
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM sys_data_role WHERE drole_id IN (${idList});")
    int deleteDataRole(@Param("idList") String  idList);

    /**
     * 获取数据角色信息
     * @param id
     * @return
     */
    @Select(" SELECT drole_id,drole_name,drole_desc,status " +
            " FROM sys_data_role WHERE drole_id = #{id}")
    SysDataRoleModel getDataRoleInfo(@Param("id") Long id);

    /**
     * 更新数据角色信息
     * @param sysDataRoleModel
     * @return
     */
    @Update(" UPDATE sys_data_role SET drole_name = #{droleName}, drole_desc = #{droleDesc},status=#{status}," +
            " last_person = #{lastPerson}, last_time = now() WHERE drole_id = #{droleId}")
    int updateDataRoleInfo(SysDataRoleModel sysDataRoleModel);


}
