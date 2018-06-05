package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.ModuleModel;
import com.youngc.pipeline.model.OrgModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Component
public interface OrgMapper {
    /**
     * 查询模块数据
     * @return
     */
    @Select(" SELECT org_id, org_name, pid" +
            " FROM sys_organize;")
    List<Map> getTree();

    /**
     * 查询模块信息
     * @param
     * @return
     */
    @Select(" SELECT org_id,org_name,pid,org_code,org_type,org_desc,status,(SELECT org_name FROM sys_organize" +
            " WHERE org_id=(SELECT pid FROM sys_organize where org_id = #{orgId}))pOrgName" +
            " FROM sys_organize where org_id = #{orgId};")
    OrgModel getOrgInfo(@Param("orgId") Long orgId);

    /**
     * 更新模块信息
     * @return
     */
    @Update(" UPDATE sys_module SET pid = #{pid}, module_name = #{moduleName}, control_id = #{controlId}, module_path = #{modulePath}," +
            " module_desc = #{moduleDesc}, type = #{type}, status = #{status}, priority = #{priority}, icon = #{icon}, "+
            " last_person = #{lastPerson}, last_time = now() "+
            " WHERE module_id = #{moduleId};")
    int update(OrgModel orgModel);

    /**
     * 添加模块信息
     * @return
     */
    @Insert(" insert into sys_module(pid,module_name,control_id,module_path,module_desc,type,status,priority,icon," +
            " add_person,add_time,last_person,last_time)" +
            " values(#{pid},#{moduleName},#{controlId},#{modulePath},#{moduleDesc},#{type},#{status},#{priority},#{icon},#{addPerson},"+
            " now(),#{lastPerson},now());")
    @Options(useGeneratedKeys = true, keyProperty = "orgId", keyColumn = "org_id")
    int addOrg(OrgModel orgModel);

    /**
     * 删除模块信息
     * @return
     */
    @Delete(" DELETE  FROM  sys_module WHERE module_id = #{moduleId}")
    int deleteOrg(@Param("orgId") Long orgId);

    @Delete(" DELETE  FROM  sys_module WHERE pid = #{moduleId}")
    int deleteOrgInfo(@Param("orgId") Long orgId);
}
