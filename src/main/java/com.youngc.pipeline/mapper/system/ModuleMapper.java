package com.youngc.pipeline.mapper.system;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ModuleMapper {
    @Select(" SELECT group_id, group_name, parent_id" +
            " FROM device_group ")
    List<Map> getTree();
}
