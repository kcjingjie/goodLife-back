package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.PipeInfoModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface InfoManagerMapper {

    //模糊检索单位下的管道设备
    @Select("SELECT device_id,unit_id,model_id,device_alias,device_name,device_code,device_desc,status,address FROM dev_info"+
            " WHERE ((device_name LIKE CONCAT('%', #{keyWord}, '%'))OR(device_alias LIKE CONCAT('%', #{keyWord}, '%')))"+
            " AND unit_id = #{pid}")
    List<PipeInfoModel> getList(@Param("keyWord")String keyWord, @Param("pid") Long pid);

    //添加设备信息
    @Insert(" INSERT INTO dev_info (unit_id, model_id, device_alias,device_name,device_code,device_desc,status,address,add_person, add_time, last_person, last_time)" +
            " VALUES(#{unitId}, #{modelId}, #{deviceAlias},#{deviceName}, #{deviceCode},#{deviceDesc},#{status},#{address},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "unit_id")
    int insert(PipeInfoModel pipeInfoModel);

    //删除设备信息
    @Delete(" DELETE FROM dev_info WHERE unit_id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询设备信息
    @Select(" SELECT device_id,unit_id,model_id,device_alias,device_name,device_code,device_desc,status,address " +
            " FROM dev_info WHERE device_id = #{id}")
    PipeInfoModel getInfo(@Param("id") Long id);

    //更新设备信息
    @Update(" UPDATE dev_info SET model_id = #{modelId}, device_alias = #{deviceAlias},device_name=#{deviceName},device_desc=#{deviceDesc}," +
            " status=#{status},address=#{address}, last_person = #{lastPerson}, last_time = now() WHERE device_id = #{id}")
    int updateInfo(PipeInfoModel pipeInfoModel);

    @Select("SELECT device_id FROM dev_info WHERE device_code=#{code}")
    PipeInfoModel getInfoByCode(@Param("code") String code);
}
