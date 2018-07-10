package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.UnitModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UnitMapper {

    /**
     * 分页获取单位信息
     *
     * @param keyword
     * @return
     */
    @Select(" SELECT su.unit_id,su.unit_code,su.unit_name,su.phone_one,su.phone_two,su.contact_one,su.contact_two,su.email," +
            " su.address,su.org_id,so.org_name FROM sys_unit su" +
            " LEFT JOIN sys_organize so on so.org_id = su.org_id" +
            " WHERE su.unit_name LIKE CONCAT('%', #{keyword}, '%')")
    List<UnitModel> getList(String keyword);

    /**
     * 获取单位信息
     *
     * @param unitId
     * @return
     */
    @Select(" SELECT su.unit_id,su.unit_code,su.unit_name,su.phone_one,su.phone_two,su.contact_one,su.contact_two,su.email," +
            " su.address,su.org_id,su.remark,so.org_name FROM sys_unit su" +
            " LEFT JOIN sys_organize so on so.org_id = su.org_id" +
            " WHERE su.unit_id = #{unitId}")
    UnitModel getUnitInfo(Long unitId);

    /**
     * 修改单位信息
     *
     * @param unitModel
     * @return
     */
    @Update(" UPDATE sys_unit SET unit_code=#{unitCode},unit_name=#{unitName},phone_one=#{phoneOne}," +
            " phone_two=#{phoneTwo},contact_one=#{contactOne},contact_two=#{contactTwo},email=#{email},address=#{address},org_id=#{orgId}" +
            " WHERE unit_id=#{unitId}")
    int updateUnitDetails(UnitModel unitModel);

    /**
     * 添加单位信息
     *
     * @param unitModel
     * @return
     */
    @Insert("INSERT INTO sys_unit (unit_code,unit_name,phone_one,phone_two,contact_one,contact_two,email,address,org_id,remark,add_person,add_time,last_person,last_time) " +
            "VALUES(#{unitCode},#{unitName},#{phoneOne},#{phoneTwo},#{contactOne},#{contactTwo},#{email},#{address},#{orgId},#{remark},#{addPerson},now(),#{lastPerson},now())")
    int addUnit(UnitModel unitModel);

    /**
     * 删除单位
     *
     * @param unitIds
     * @return
     */
    @Delete("DELETE FROM sys_unit WHERE unit_id IN (${unitIds})")
    int deleteUnitList(@Param("unitIds") String unitIds);

    /**
     * 检验编码是否重复
     *
     * @param code
     * @return
     */
    @Select("SELECT unit_id FROM sys_unit WHERE unit_code=#{code}")
    UnitModel getUnitByCode(String code);
}
