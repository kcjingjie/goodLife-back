package com.youngc.pipeline.mapper.system;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ModuleMapper {
    @Select(" SELECT module_id, module_name, pid" +
            " FROM sys_module ")
    List<Map> getTree();
}
