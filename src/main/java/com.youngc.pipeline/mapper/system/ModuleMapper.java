package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.ModuleModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author
 */
@Component
public interface ModuleMapper {
    @Select(" SELECT module_id, module_name, pid" +
            " FROM sys_module ")
    List<Map> getTree();

    @Select(" select module_id,pid,module_name,control_id,module_path,module_desc,type,status,priority,icon" +
            " FROM sys_module where module_id = #{moduleId};")
    ModuleModel getModuleInfo(@Param("moduleId") Long moduleId);

    @Update(" UPDATE sys_module SET pid = #{pid}, module_name = #{moduleName}, control_id = #{controlId}, module_path = #{modulePath}," +
            " module_desc = #{moduleDesc}, type = #{type}, status = #{status}, priority = #{priority}, icon = #{icon}, "+
            " last_person = #{lastPerson}, last_time = #{lastTime} "+
            " WHERE module_id = #{moduleId};")
    int update(ModuleModel moduleModel);

    @Insert(" insert into sys_module(pid,module_name,control_id,module_path,module_desc,type,status,priority,icon," +
            " add_person,add_time,last_person,last_time)" +
            " values(#{pid},#{moduleName},#{controlId},#{modulePath},#{moduleDesc},#{type},#{status},#{priority},#{icon},#{addPerson},"+
            " #{addTime},#{lastPerson},#{lastTime});")
    @Options(useGeneratedKeys = true, keyProperty = "moduleId", keyColumn = "module_id")
    int addModule(ModuleModel moduleModel);

    @Delete(" DELETE  FROM  sys_module WHERE module_id = #{moduleId}")
    int deleteModule(@Param("moduleId") Long moduleId);
}
