package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevModelConfigParaModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevModelConfigParaMapper {
    //检索设备标准参数信息
    @Select("SELECT dmcp.id,para_name,para_id,para_value,para_unit,para_type,sda.data_name type_name from dev_model_config_para dmcp " +
            " LEFT JOIN dev_model dm on dm.model_id=dmcp.model_id " +
            " LEFT JOIN sys_dict_data sda on sda.dict_value='para_type' AND sda.data_value=dmcp.para_type " +
            " WHERE dmcp.model_id=#{modelId}")
    List<DevModelConfigParaModel> getList(@Param("modelId") Long modelId);

    //添加设备标准参数信息
    @Insert(" INSERT INTO dev_model_config_para (model_id, para_name, para_id,para_value,para_unit,para_type,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{modelId}, #{paraName}, #{paraId},#{paraValue}, #{paraUnit},#{paraType},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevModelConfigParaModel devModelConfigParaModel);

    //删除设备标准参数信息
    @Delete(" DELETE FROM dev_model_config_para WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询设备标准参数信息
    @Select(" SELECT id,para_name,para_id,para_value,para_unit,para_type,remark" +
            " FROM dev_model_config_para WHERE id = #{id}")
    DevModelConfigParaModel getInfo(@Param("id") Long id);

    //更新设备标准参数信息
    @Update(" UPDATE dev_model_config_para SET para_name = #{paraName}, para_id = #{paraId},para_value=#{paraValue},para_unit=#{paraUnit}," +
            " para_type=#{paraType},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevModelConfigParaModel devModelConfigParaModel);

    //根据编号查询是否存在该设备标准参数
    @Select("SELECT id FROM dev_model_config_para WHERE model_id=#{modelId} AND para_id=#{paraId}")
    DevModelConfigParaModel getInfoByCode(@Param("modelId") Long modelId,@Param("paraId") String paraId);
}
