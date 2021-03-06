package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.ImageModel;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.model.TypeManageModel;
import com.youngc.pipeline.sqlProvider.system.SystemSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface InfoManagerMapper {

    /**
     * 模糊检索设备信息
     * @param keyWord
     * @param pid
     * @return
     */
    @Select("SELECT device_id,unit_id,device_alias,device_name,pressure_pipe,device_equip,device_type,device_desc,dev_info.status  FROM dev_info"+
            " WHERE ((device_name LIKE CONCAT('%', #{keyWord}, '%'))OR(device_alias LIKE CONCAT('%', #{keyWord}, '%')))"+
            " AND unit_id = #{pid}")
    List<PipeInfoModel> getList(@Param("keyWord")String keyWord, @Param("pid") Long pid);

    /**
     * 添加设备信息
     * @param pipeInfoModel
     * @return
     */
    @Insert(" INSERT INTO dev_info (unit_id,device_type,device_equip, device_alias,device_name,pressure_pipe,device_desc,status,add_person, add_time, last_person, last_time)" +
            " VALUES(#{unitId},#{deviceType},#{deviceEquip}, #{deviceAlias},#{deviceName}, #{pressurePipe},#{deviceDesc},#{status},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "device_id")
    int insert(PipeInfoModel pipeInfoModel);

    /**
     *复制设备模型下的标准参数信息，添加到设备标准参数信息表中
     */
    @Insert(" INSERT INTO dev_config_para ( para_name, para_id,para_value,para_unit,para_type,remark,device_id,add_person, add_time, last_person, last_time)" +
            " SELECT para_name, para_id,para_value,para_unit,para_type,remark,#{deviceId},#{personId},now(),#{personId},now() from dev_model_config_para where model_id=#{modelId} ")
    int insertConfigParas(@Param("deviceId")Long deviceId,@Param("modelId")Long modelId,@Param("personId")Long personId);

    /**
     *复制设备模型下的监测参数信息，添加到设备监测参数信息表中
     */
    @Insert(" INSERT INTO dev_mon_para ( para_name, para_id,para_unit,para_type,para_data_type,remark,device_id,add_person, add_time, last_person, last_time)" +
            " SELECT para_name, para_id,para_unit,para_type,para_data_type,remark,#{deviceId},#{personId},now(),#{personId},now() from dev_model_mon_para where model_id=#{modelId} ")
    int insertMonParas(@Param("deviceId")Long deviceId,@Param("modelId")Long modelId,@Param("personId")Long personId);

    /**
     * 删除设备信息
     */
    @Delete(" DELETE FROM dev_info WHERE device_id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 删除设备下的标准参数信息
     */
    @Delete(" DELETE FROM dev_config_para WHERE device_id IN (${idList});")
    int deleteConfigPara(@Param("idList") String  idList);

    /**
     * 删除设备下监测参数信息
     */
    @Delete(" DELETE FROM dev_mon_para WHERE device_id IN (${idList});")
    int deleteMonPara(@Param("idList") String  idList);

    /**
     * 根据设备id查询设备信息
     * @param id
     * @return
     */
    @Select(" SELECT device_id,unit_id,device_type,device_equip,device_alias,device_name,pressure_pipe,device_desc,status " +
            " FROM dev_info WHERE device_id = #{id}")
    PipeInfoModel getInfo(@Param("id") Long id);

    /**
     * 修改设备信息
     */
    @Update(" UPDATE dev_info SET device_type=#{deviceType},device_equip=#{deviceEquip}, device_alias = #{deviceAlias},device_name=#{deviceName},pressure_pipe=#{pressurePipe},device_desc=#{deviceDesc}," +
            " status=#{status}, last_person = #{lastPerson}, last_time = now() WHERE device_id = #{deviceId}")
    int updateInfo(PipeInfoModel pipeInfoModel);

    /**
     * 查询设备编号是否唯一
     * @param code
     * @return
     */
    @Select("SELECT device_id FROM dev_info WHERE device_name=#{code}")
    List<PipeInfoModel> getInfoByCode(@Param("code") String code);

    /**
     * 查询设备模型id，模型名称
     * @return
     */
    @Select("SELECT model_id,model_name FROM dev_model ")
    List<TypeManageModel> getDevModel();

    /**
     * 查询设备单管图地址
     */
    @Select("SELECT image_url from dev_info LEFT JOIN sys_image " +
            " on sys_image.id=dev_info.image_id WHERE device_id=#{deviceId};")
    ImageModel getImageUrl(@Param("deviceId") Long deviceId);

    /**
     *查询所有不重复的参数名称
     */
    @Select("select DISTINCT para_name FROM dev_config_para order by para_id;")
    List<DevConfigParaModel> getParaName();

    /**
     * 根据单位id查询标准参数信息
     */
    @Select("SELECT di.device_id,dcp.para_name,dcp.para_value,dcp.para_unit from  dev_info di " +
            "LEFT JOIN dev_config_para dcp on di.device_id=dcp.device_id WHERE di.unit_id=#{unitId}")
    List<DevConfigParaModel> getParaValue(@Param("unitId") Long  unitId);

    @Select("SELECT file_id,file_name,folder_id,file_path,type From dev_file WHERE dev_id=#{devId}")
    List<Map> getFileById(Long devId);

    /**
     * 获取标准参数详情
     * @param devId
     * @return
     */
    @Select("SELECT id,para_name,para_id,para_value,para_unit FROM dev_config_para WHERE device_id=#{devId}")
    List<DevConfigParaModel> getDeatail(Long devId);

    /**
     * Excel导出
     * @param unitId
     * @return
     */
    @Select("SELECT di.device_id,di.device_name,di.device_equip,di.device_type,dcp.para_name,dcp.para_value,dcp.para_unit,sdd1.data_name as deviceEquipName,sdd2.data_name as deviceTypeName from  dev_info di " +
            " LEFT JOIN dev_config_para dcp on di.device_id=dcp.device_id "+
            " LEFT JOIN sys_dict_data sdd1 on sdd1.dict_value='device_equip'&& sdd1.data_value=di.device_equip "+
            " LEFT JOIN sys_dict_data sdd2 on sdd2.dict_value='deviceType'&& sdd2.data_value=di.device_type"+
            " WHERE di.unit_id=#{unitId} ORDER BY di.device_name")
    List<PipeInfoModel> excelDownload(Long unitId);

    /**
     * 导入管道信息
     * @param pipeInfoModels
     * @param userId
     * @param unitId
     * @return
     */
    @InsertProvider(type = SystemSqlProvider.class, method ="addDevInfoByExcel1")
    int addDevInfoByExcel(List<PipeInfoModel> pipeInfoModels,Long userId,Long unitId);

    /**
     * 导入管道标准参数
     * @param devConfigParaModels
     * @param userId
     * @return
     */
    @InsertProvider(type = SystemSqlProvider.class, method = "addDevConfigParaByExcel")
    int addDevConfigParaByExcel(List<DevConfigParaModel> devConfigParaModels,Long userId,Long unitId);
}
