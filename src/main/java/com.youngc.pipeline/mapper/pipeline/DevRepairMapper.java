package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevRepairModel;
import com.youngc.pipeline.sqlProvider.system.SystemSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevRepairMapper {

    /**
     * 分页获取设备标准参数信息
     * @param deviceId
     * @return
     */
    @Select("SELECT id,device_id,manufactor,model,specification,material,company,brand,stock,quantity,cycle,price from dev_repair dr " +
            " WHERE dr.device_id=#{deviceId}")
    List<DevRepairModel> getList(@Param("deviceId") Long deviceId);

    /**
     * 根据id查询设备备件
     * @param id
     * @return
     */
    @Select(" SELECT id,device_id,manufactor,model,specification,material,company,brand,stock,quantity,cycle,price from dev_repair dr " +
            " WHERE id = #{id}")
    DevRepairModel getInfo(@Param("id") Long id);

    /**
     * 修改设备标备件
     * @param devRepairModel
     * @return
     */
    @Update(" UPDATE dev_repair SET manufactor = #{manufactor}, model = #{model},specification=#{specification},material=#{material}," +
            " company=#{company},brand=#{brand}, stock = #{stock}, quantity=#{quantity},cycle=#{cycle},price=#{price},last_person=#{lastPerson},last_time = now() WHERE id = #{id}")
    int updateInfo(DevRepairModel devRepairModel);

    /**
     * 添加设备标准参数信息
     * @param devRepairModel
     * @return
     */
    @Insert(" INSERT INTO dev_repair (device_id, manufactor, model,specification,material,company,brand,stock,quantity,cycle,price,add_person, add_time, last_person, last_time)" +
            " VALUES(#{deviceId}, #{manufactor}, #{model},#{specification}, #{material},#{company},#{brand},#{stock},#{quantity},#{cycle},#{price},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevRepairModel devRepairModel);

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM dev_repair WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 添加设备管件信息
     * @param data
     * @param userId
     * @param devId
     * @return
     */
    @InsertProvider(type = SystemSqlProvider.class, method = "readDevRepairExcel")
    boolean readExcel(List<DevRepairModel> data,Long userId,Long devId);

    @Select("SELECT manufactor,model,specification,material,company,brand,stock,quantity,cycle,price from dev_repair WHERE device_id=#{devId}")
    List<DevRepairModel> getExcel(Long devId);
}
