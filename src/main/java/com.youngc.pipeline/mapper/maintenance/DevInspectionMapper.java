package com.youngc.pipeline.mapper.maintenance;

import com.youngc.pipeline.model.DevInspectionModel;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.model.TypeManageModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevInspectionMapper {

    //模糊检索巡检计划信息
    @Select("SELECT id,dev_id,plan_name,exe_time,exe_cycle,exe_user,exe_desc,remark,device_name "+
          "  from dev_inspection_plan dip  "+
            " LEFT JOIN dev_info di ON di.device_id=dip.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') "+
            " AND plan_name LIKE CONCAT('%',#{keyWord},'%')")
    List<DevInspectionModel> getList(@Param("keyWord")String keyWord, @Param("devName") String  devName);

    //添加计划信息、
    @Insert(" INSERT INTO dev_inspection_plan (plan_name, dev_id, exe_time,exe_cycle,exe_user,exe_desc,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{planName}, #{devId}, #{exeTime},#{exeCycle}, #{exeUser},#{exeDesc},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevInspectionModel devInspectionModel);

    //删除计划信息
    @Delete(" DELETE FROM dev_inspection_plan WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询计划信息
    @Select(" SELECT id,dev_id,plan_name,exe_time,exe_cycle,exe_user,exe_desc,remark " +
            " FROM dev_inspection_plan WHERE id = #{id}")
    DevInspectionModel getInfo(@Param("id") Long id);

    //修改计划信息
    @Update(" UPDATE dev_inspection_plan SET dev_id = #{devId}, plan_name = #{planName},exe_time=#{exeTime},exe_cycle=#{exeCycle}," +
            " exe_user=#{exeUser},exe_desc=#{exeDesc},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevInspectionModel devInspectionModel);

    //查询所有设备
    @Select("SELECT device_id,device_name FROM dev_info ")
    List<PipeInfoModel> getDevList();

}
