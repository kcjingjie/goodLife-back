package com.youngc.pipeline.mapper.maintenance;

import com.youngc.pipeline.model.DevCheckModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevCheckMapper {
    //模糊检索检验计划信息
    @Select("SELECT id,dev_id,plan_name,exe_time,exe_cycle,exe_user,exe_desc,remark,device_name,check_organize,check_user "+
            "  from dev_check_plan dcp  "+
            " LEFT JOIN dev_info di ON di.device_id=dcp.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') "+
            " AND plan_name LIKE CONCAT('%',#{keyWord},'%')")
    List<DevCheckModel> getList(@Param("keyWord")String keyWord, @Param("devName") String  devName);

    //添加计划信息、
    @Insert(" INSERT INTO dev_check_plan (plan_name, dev_id, exe_time,exe_cycle,check_organize,check_user,exe_user,exe_desc,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{planName}, #{devId}, #{exeTime},#{exeCycle},#{checkOrganize},#{checkUser}, #{exeUser},#{exeDesc},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevCheckModel devCheckModel);

    //删除计划信息
    @Delete(" DELETE FROM dev_check_plan WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    //根据id查询计划信息
    @Select(" SELECT id,dev_id,plan_name,exe_time,exe_cycle,exe_user,exe_desc,remark,check_organize,check_user " +
            " FROM dev_check_plan WHERE id = #{id}")
    DevCheckModel getInfo(@Param("id") Long id);

    //修改计划信息
    @Update(" UPDATE dev_check_plan SET dev_id = #{devId}, plan_name = #{planName},exe_time=#{exeTime},exe_cycle=#{exeCycle}," +
            " exe_user=#{exeUser},exe_desc=#{exeDesc},check_organize=#{checkOrganize},check_user=#{checkUser},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevCheckModel devCheckModel);

}
