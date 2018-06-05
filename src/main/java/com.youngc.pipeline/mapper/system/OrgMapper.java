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
     * 查询组织数据
     * @return
     */
    @Select(" SELECT org_id, org_name, pid" +
            " FROM sys_organize;")
    List<Map> getTree();

    /**
     * 查询组织信息
     * @param
     * @return
     */
    @Select(" SELECT org_id,org_name,pid,org_code,org_desc,(SELECT org_name FROM sys_organize" +
            " WHERE org_id=(SELECT pid FROM sys_organize where org_id = #{orgId}))pOrgName" +
            " FROM sys_organize where org_id = #{orgId};")
    OrgModel getOrgInfo(@Param("orgId") Long orgId);

    /**
     * 更新组织信息
     * @return
     */
    @Update(" UPDATE sys_organize SET pid = #{pid}, org_name = #{orgName}, org_code = #{orgCode}, org_desc = #{orgDesc}," +
            " last_person = #{lastPerson}, last_time = now() "+
            " WHERE org_id = #{orgId};")
    int updateOrg(OrgModel orgModel);

    /**
     * 添加组织信息
     * @return
     */
    @Insert(" insert into sys_organize(org_code,pid,org_name,org_desc,add_person,add_time,last_person,last_time)" +
            " values(#{orgCode},#{pid},#{orgName},#{orgDesc},#{addPerson},now(),#{lastPerson},now());")
    @Options(useGeneratedKeys = true, keyProperty = "orgId", keyColumn = "org_id")
    int addOrg(OrgModel orgModel);

    /**
     * 删除组织信息
     * @return
     */
    @Delete(" DELETE  FROM  sys_organize WHERE org_id = #{orgId}")
    int deleteOrg(@Param("orgId") Long orgId);

    @Delete(" DELETE  FROM  sys_organize WHERE pid = #{orgId}")
    int deleteOrgInfo(@Param("orgId") Long orgId);
}
