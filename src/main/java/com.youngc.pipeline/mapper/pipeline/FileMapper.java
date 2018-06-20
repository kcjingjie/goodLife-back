package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.FileModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author
 */
@Component
public interface FileMapper {

    /**
     * 查询设备信息
     *
     * @param
     * @return
     */
    @Select(" SELECT device_id,unit_id,device_name FROM dev_info ;")
    List<Map> getDevTree();


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
     * 根据设备ID查询设备下文件信息
     *
     * @param
     * @return
     */
    @Select(" SELECT type,file_id,file_name,dev_id,file_path,last_time" +
            " FROM dev_file where dev_id IN (${deviceIds}) and folder_id = 0;")
    List<FileModel> getFileInfoByDevId(@Param("deviceIds") String deviceIds);

    /**
     * 添加文件夹信息
     *
     * @param
     * @return
     */
    @Select(" INSERT INTO dev_file(file_name,folder_id,dev_id,file_path,type,add_person,add_time,last_person,last_time)" +
            " VALUES(#{fileName},#{folderId},#{devId},#{filePath},#{type},#{userId},now(),#{userId},now());")
    FileModel postFolder(FileModel fileModel);

    /**
     * 删除档案信息
     *
     * @param
     * @return
     */
    @Select(" DELETE FROM dev_file WHERE file_id = #{fileId} OR folder_id = #{fileId}; ")
    void deleteFile(@Param("fileId") String fileId);

    /**
     * 查询文件夹下文件信息
     *
     * @param
     * @return
     */
    @Select(" SELECT type,file_id,file_name,dev_id,file_path,last_time" +
            " FROM dev_file where folder_id = #{fileId};")
    List<FileModel> getFolderFileInfo(@Param("fileId") Long fileId);
}
