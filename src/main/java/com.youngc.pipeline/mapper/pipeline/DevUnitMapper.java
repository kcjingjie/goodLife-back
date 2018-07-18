package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.sqlProvider.system.SystemSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevUnitMapper {
    /**
     * 分页获取设备管件信息
     * @param deviceId
     * @return
     */
    @Select("SELECT du.id,di.device_id,unit_name,unit_version,unit_number,unit_material from dev_unit du " +
            " LEFT JOIN dev_info di on di.device_id=du.device_id " +
            " WHERE du.device_id=#{deviceId}")
    List<DevUnitModel> getList(@Param("deviceId") Long deviceId);

    /**
     * 添加设备管件信息
     * @param devUnitModel
     * @return
     */
    @Insert(" INSERT INTO dev_unit (device_id, unit_name,unit_version,unit_number,unit_material,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{deviceId}, #{unitName}, #{unitVersion},#{unitNumber}, #{unitMaterial},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevUnitModel devUnitModel);

    /**
     * 删除设备管件信息
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM dev_unit WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 根据id查询设备管件信息
     * @param id
     * @return
     */
    @Select(" SELECT id,unit_name,unit_version,unit_number,unit_material,remark" +
            " FROM dev_unit WHERE id = #{id}")
    DevUnitModel getInfo(@Param("id") Long id);

    /**
     * 修改设备管件信息
     * @param devUnitModel
     * @return
     */
    @Update(" UPDATE dev_unit SET unit_name = #{unitName}, unit_version = #{unitVersion},unit_number=#{unitNumber}," +
            " unit_material=#{unitMaterial},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevUnitModel devUnitModel);

    /**
     * 添加设备管件信息
     * @param data
     * @param userId
     * @param devId
     * @return
     */
    @InsertProvider(type = SystemSqlProvider.class, method = "readDevUnitExcel")
    boolean readExcel(List<DevUnitModel> data,Long userId,Long devId);
}
