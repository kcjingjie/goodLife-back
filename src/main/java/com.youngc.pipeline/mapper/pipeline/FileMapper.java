package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author
 */
@Component
public interface FileMapper {


    /**
     * 根据部门ID查询单位Id
     *
     * @param
     * @return
     */
    @Select(" SELECT GROUP_CONCAT(unit_id separator ',')unit_ids FROM sys_unit " +
            " WHERE FIND_IN_SET(org_id,( " +
            " SELECT GROUP_CONCAT(org_id separator ',') org_id FROM sys_organize " +
            " WHERE  FIND_IN_SET(org_id,getChildList(#{orgId}))));")
    String getUnitIdByOrgId(@Param("orgId") Long orgId);

    /**
     * 根据单位ID查询设备Id
     *
     * @param
     * @return
     */
    @Select(" SELECT GROUP_CONCAT(device_id separator ',')device_id FROM dev_info WHERE unit_id IN (${unitIds});")
    String getDevIdByUnitId(@Param("unitIds") String unitIds);

    /**
     * 根据单位ID查询设备下文件信息
     *
     * @param
     * @return
     */
    @Select(" SELECT type,file_id,file_name,dev_id,file_path,last_time" +
            " FROM dev_file where dev_id IN (${deviceIds});")
    List<FileModel> getFileInfoByDevId(@Param("deviceIds") String deviceIds);
}
