package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.UnitModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UnitMapper {

    /**
     * 分页获取单位信息
     * @param keyword
     * @return
     */
    @Select(" SELECT sys_unit.unit_id,sys_unit.unit_code,sys_unit.unit_name,sys_unit.unit_name_all,sys_unit.phone_one,sys_unit.phone_two,sys_unit.contact_one,sys_unit.contact_two,sys_unit.email,sys_unit.address,sys_unit.org_id,sys_organize.org_name FROM sys_unit,sys_organize" +
            " WHERE (((sys_unit.unit_name LIKE CONCAT('%', #{keyword}, '%')) OR (sys_unit.unit_name_all LIKE CONCAT('%', #{keyword}, '%')))AND sys_unit.org_id=sys_organize.org_id)")
    List<UnitModel> getList(String keyword);

    /**
     * 获取单位信息
     * @param unitId
     * @return
     */
    @Select(" SELECT sys_unit.unit_id,sys_unit.unit_code,sys_unit.unit_name,sys_unit.unit_name_all,sys_unit.phone_one,sys_unit.phone_two,sys_unit.contact_one,sys_unit.contact_two,sys_unit.email,sys_unit.address,sys_unit.org_id,sys_unit.remark,sys_organize.org_name FROM sys_unit,sys_organize" +
            " WHERE (sys_unit.org_id=#{unitId} AND sys_unit.org_id=sys_organize.org_id)")
    UnitModel getUnitInfo(Long unitId);

    /**
     * 修改单位信息
     * @param unitModel
     * @return
     */
    @Update("UPDATE sys_unit SET unit_code=#{unitCode},unit_name=#{unitName},unit_name_all=#{unitNameAll},phone_one=#{phoneOne},phone_two=#{phoneTwo},contact_one=#{contactOne},contact_two=#{contactTwo},email=#{email},address=#{address},org_id=#{orgId}"+
    " WHERE unit_id=#{unitId}")
    int updateUnitDetails(UnitModel unitModel);

    /**
     * 添加单位信息
     * @param unitModel
     * @return
     */
    @Insert("INSERT INTO sys_unit (unit_code,unit_name,unit_name_all,phone_one,phone_two,contact_one,contact_two,email,address,org_id,remark,add_person,add_time,last_person,last_time) "+
    "VALUES(#{unitCode},#{unitName},#{unitNameAll},#{phoneOne},#{phoneTwo},#{contactOne},#{contactTwo},#{email},#{address},#{orgId},#{remark},#{addPerson},now(),#{lastPerson},now())")
    int addUnit(UnitModel unitModel);

    /**
     * 删除单位
     * @param unitIds
     * @return
     */
    @Delete("DELETE FROM sys_unit WHERE unit_id IN (${unitIds})")
    int deleteUnitList(@Param("unitIds") String unitIds);
}
