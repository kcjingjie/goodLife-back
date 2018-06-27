package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevModelConfigParaModel;
import com.youngc.pipeline.model.DevModelMonParaModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevModelMonParaMapper {
    //检索设备标准参数信息
    @Select("SELECT dmmp.id,para_name,para_id,para_value,para_unit,para_type,sda.data_name type_name,para_data_type,sda2.data_name dataName from dev_model_mon_para dmmp " +
            " LEFT JOIN dev_model dm on dm.model_id=dmmp.model_id " +
            " LEFT JOIN sys_dict_data sda on sda.dict_value='para_type' AND sda.data_value=dmmp.para_type " +
            " LEFT JOIN sys_dict_data sda2 on sda2.dict_value='para_data_type' AND sda2.data_value=dmmp.para_data_type " +
            " WHERE dmmp.model_id=#{modelId}")
    List<DevModelMonParaModel> getList(@Param("modelId") Long modelId);

    //添加设备标准参数信息
    @Insert(" INSERT INTO dev_model_mon_para (model_id, para_name, para_id,para_unit,para_type,para_data_type,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{modelId}, #{paraName}, #{paraId}, #{paraUnit},#{paraType},#{paraDataType},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevModelMonParaModel devModelMonParaModel);

    //删除设备标准参数信息
    @Delete(" DELETE FROM dev_model_mon_para WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询设备标准参数信息
    @Select(" SELECT id,para_name,para_id,para_data_type,para_unit,para_type,remark" +
            " FROM dev_model_mon_para WHERE id = #{id}")
    DevModelMonParaModel getInfo(@Param("id") Long id);

    //更新设备标准参数信息
    @Update(" UPDATE dev_model_mon_para SET para_name = #{paraName}, para_id = #{paraId},para_data_type,=#{paraDataType},para_unit=#{paraUnit}," +
            " para_type=#{paraType},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevModelMonParaModel devModelMonParaModel);

    //根据编号查询是否存在该设备标准参数
    @Select("SELECT id FROM dev_model_mon_para WHERE model_id=#{modelId} AND para_id=#{paraId}")
    DevModelMonParaModel getInfoByCode(@Param("modelId") Long modelId,@Param("paraId") String paraId);
}
