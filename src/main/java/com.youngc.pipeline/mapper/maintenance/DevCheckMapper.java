package com.youngc.pipeline.mapper.maintenance;

import com.youngc.pipeline.model.DevCheckModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevCheckMapper {
    /**
     * 模糊检索检验计划信息
     */
    @Select("SELECT id,dev_id,device_equip,device_type,check_result,last_exe_time,exe_cycle,plan_exe_time,delay_time,remark,device_name,check_organize,delay_reason,check_report "+
            "  from dev_check_plan dcp  "+
            " LEFT JOIN dev_info di ON di.device_id=dcp.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') ")
    List<DevCheckModel> getList( @Param("devName") String  devName);

    /**
     * 模糊检索检验计划信息
     */
    @Select("SELECT id,dev_id,device_equip,device_type,last_exe_time,exe_cycle,plan_exe_time,delay_time,remark,device_name,check_organize,delay_reason,check_report "+
            "  from dev_check_plan dcp  "+
            " LEFT JOIN dev_info di ON di.device_id=dcp.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') "+
            " AND datediff(plan_exe_time,now())<=7 "+
            " AND datediff(plan_exe_time,now())>=0")
    List<DevCheckModel> getNeedList( @Param("devName") String  devName);


    /**
     * 添加计划信息
     */
    @Insert(" INSERT INTO dev_check_plan ( dev_id, plan_exe_time,exe_cycle,check_organize,remark,add_person, add_time, last_person, last_time)" +
            " VALUES( #{devId}, #{planExeTime},#{exeCycle},#{checkOrganize},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevCheckModel devCheckModel);

    /**
     * 删除计划信息
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM dev_check_plan WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 根据id查询计划信息
     * @param id
     * @return
     */
    @Select(" SELECT id,dev_id, plan_exe_time,exe_cycle,check_organize,remark " +
            " FROM dev_check_plan WHERE id = #{id}")
    DevCheckModel getInfo(@Param("id") Long id);

    /**
     * 修改计划信息
     * @param devCheckModel
     * @return
     */
    @Update(" UPDATE dev_check_plan SET dev_id = #{devId},plan_exe_time=#{planExeTime},exe_cycle=#{exeCycle}," +
            " check_organize=#{checkOrganize},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevCheckModel devCheckModel);

    /**
     * 提交计划信息
     * @param devCheckModel
     * @return
     */
    @Update(" UPDATE dev_check_plan SET last_exe_time = #{lastExeTime},plan_exe_time=#{planExeTime},check_result=#{checkResult},check_report=#{checkReport}," +
            " delay_reason=#{delayReason},last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int submitInfo(DevCheckModel devCheckModel);

}
