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
}
