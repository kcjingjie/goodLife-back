package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevMonParaModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevMonParaMapper {
    /**
     * 分页获取设备监测参数信息
     * @param deviceId
     * @return
     */
    @Select("SELECT dmp.id,para_name,para_id,para_value,para_unit,para_type,para_value_up,para_value_down,sda.data_name type_name from dev_mon_para dmp " +
            " LEFT JOIN dev_info di on di.device_id=dmp.device_id " +
            " LEFT JOIN sys_dict_data sda on sda.dict_value='para_type' AND sda.data_value=dmp.para_type " +
            " WHERE dmp.device_id=#{deviceId}")
    List<DevMonParaModel> getList(@Param("deviceId") Long deviceId);

    /**
     * 添加设备监测参数信息
     * @param devMonParaBean
     * @return
     */
    @Insert(" INSERT INTO dev_mon_para (device_id, para_name, para_id,para_value,para_unit,para_value_up,para_value_down,para_type,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{deviceId}, #{paraName}, #{paraId},#{paraValue}, #{paraUnit},#{paraValueUp},#{paraValueDown},#{paraType},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevMonParaModel devMonParaModel);

    /**
     * 删除设备监测参数信息
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM dev_mon_para WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 根据id获取设备监测参数信息
     * @param id
     * @return
     */
    @Select(" SELECT id,para_name,para_id,para_value,para_unit,para_value_up,para_value_down,para_type,remark" +
            " FROM dev_mon_para WHERE id = #{id}")
    DevMonParaModel getInfo(@Param("id") Long id);

    /**
     * 修改设备监测参数信息
     * @param devMonParaBean
     * @return
     */
    @Update(" UPDATE dev_mon_para SET para_name = #{paraName}, para_id = #{paraId},para_value_up=#{paraValueUp},para_value_down=#{paraValueDown},para_value=#{paraValue},para_unit=#{paraUnit}," +
            " para_type=#{paraType},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevMonParaModel devMonParaModel);

    /**
     * 查询设备监测参数的标识是否唯一
     * @param deviceId
     * @param paraId
     * @return
     */
    @Select("SELECT id FROM dev_mon_para WHERE device_id=#{deviceId} AND para_id=#{paraId}")
    DevMonParaModel getInfoByCode(@Param("deviceId") Long deviceId,@Param("paraId") String paraId);
}
