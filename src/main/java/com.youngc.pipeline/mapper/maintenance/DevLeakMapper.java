package com.youngc.pipeline.mapper.maintenance;

import com.youngc.pipeline.model.DevLeakModel;
import com.youngc.pipeline.model.PipeInfoModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevLeakMapper {

    /**
     * 模糊检索泄漏点管理
     * @param keyWord
     * @param devName
     * @return
     */
    @Select("SELECT id,dev_id,device_name,leak_no,leak_degree,occur_time,plan_exe_time,dlm.status,handle_method FROM dev_leak_manager dlm "+
            " LEFT JOIN dev_info di ON di.device_id=dlm.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') "+
            " AND leak_no LIKE CONCAT('%',#{keyWord},'%')")
    List<DevLeakModel> getList(@Param("keyWord")String keyWord, @Param("devName") String  devName);

    /**
     * 模糊检索泄漏点管理
     * @param keyWord
     * @param devName
     * @return
     */
    @Select("SELECT id,dev_id,device_name,leak_no,leak_degree,occur_time,plan_exe_time,dlm.status,handle_method FROM dev_leak_manager dlm "+
            " LEFT JOIN dev_info di ON di.device_id=dlm.dev_id  "+
            " WHERE device_name LIKE CONCAT('%', #{devName}, '%') "+
            " AND leak_no LIKE CONCAT('%',#{keyWord},'%')" +
            " AND dlm.status=1"+
            " AND datediff(now(),plan_exe_time)<=7")
    List<DevLeakModel> getUnhandle(@Param("keyWord")String keyWord, @Param("devName") String  devName);

    /**
     * 添加泄漏点信息
     * @param devLeakModel
     * @return
     */
    @Insert(" INSERT INTO dev_leak_manager (dev_id, leak_no,leak_degree,occur_time,plan_exe_time,status,handle_method,remark,add_person, add_time, last_person, last_time)" +
            " VALUES( #{devId}, #{leakNo},#{leakDegree}, #{occurTime},#{planExeTime},1,#{handleMethod},#{remark},#{addPerson}, now(), #{lastPerson}, now())")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(DevLeakModel devLeakModel);

    /**
     * 删除泄漏点信息
     * @param idList
     * @return
     */
    @Delete(" DELETE FROM dev_leak_manager WHERE id IN (${idList});")
    int delete(@Param("idList") String  idList);

    /**
     * 根据id查询泄漏点信息
     * @param id
     * @return
     */
    @Select(" SELECT id,dev_id,leak_no,leak_degree,occur_time,plan_exe_time,status,handle_method,remark" +
            " FROM dev_leak_manager WHERE id = #{id}")
    DevLeakModel getInfo(@Param("id") Long id);

    /**
     * 修改泄漏点信息
     * @param devLeakModel
     * @return
     */
    @Update(" UPDATE dev_leak_manager SET dev_id = #{devId}, leak_no = #{leakNo},leak_degree=#{leakDegree},occur_time=#{occurTime}," +
            " plan_exe_time=#{planExeTime},handle_method=#{handleMethod},remark=#{remark}, last_person = #{lastPerson}, last_time = now() WHERE id = #{id}")
    int updateInfo(DevLeakModel devLeakModel);

    /**
     * 查询所有设备
     */
    @Select("SELECT device_id,device_name FROM dev_info ")
    List<PipeInfoModel> getDevList();

    /**
     * 查询编号是否重复
     */
    @Select(" SELECT id, leak_no" +
            " FROM dev_leak_manager WHERE leak_no = #{leakNo}")
    List<DevLeakModel> getInfoByNo(@Param("leakNo") String leakNo);

    /**
     * 修改状态
     * @param id
     * @return
     */
    @Update("UPDATE dev_leak_manager SET status=2 WHERE id = #{id}")
    boolean changeStatus(Long id);
}
