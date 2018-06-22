package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.PipeInfoModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevConfigParaMapper {
    //检索设备标准参数信息
    @Select("SELECT dcp.id,para_name,para_id,para_value,para_unit,para_type,sda.data_name type_name from dev_config_para dcp " +
            " LEFT JOIN dev_info di on di.device_id=dcp.device_id " +
            " LEFT JOIN sys_dict_data sda on sda.dict_value='config_type' AND sda.data_value=dcp.para_type " +
            " WHERE dcp.device_id=#{deviceId}")
    List<DevConfigParaModel> getList(@Param("deviceId") Long deviceId);

    //添加设备标准参数信息
    @Insert(" INSERT INTO dev_config_para (device_id, para_name, para_id,para_value,para_unit,para_type,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{deviceId}, #{paraName}, #{paraId},#{paraValue}, #{paraUnit},#{paraType},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevConfigParaModel devConfigParaModel);

    //删除设备标准参数信息
    @Delete(" DELETE FROM dev_config_para WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询设备标准参数信息
    @Select(" SELECT id,para_name,para_id,para_value,para_unit,para_type,remark" +
            " FROM dev_config_para WHERE id = #{id}")
    DevConfigParaModel getInfo(@Param("id") Long id);

    //更新设备标准参数信息
    @Update(" UPDATE dev_config_para SET para_name = #{paraName}, para_id = #{paraId},para_value=#{paraValue},para_unit=#{paraUnit}," +
            " para_type=#{paraType},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevConfigParaModel devConfigParaModel);

    //根据编号查询是否存在该设备标准参数
    @Select("SELECT id FROM dev_config_para WHERE device_id=#{deviceId} AND para_id=#{paraId}")
    DevConfigParaModel getInfoByCode(@Param("deviceId") Long deviceId,@Param("paraId") String paraId);

}

