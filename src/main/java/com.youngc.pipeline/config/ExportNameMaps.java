package com.youngc.pipeline.config;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ExportNameMaps {


    public Map<String, String> modelPoint = new LinkedHashMap<String, String>() {{
        put("para_name", "监测参数名称");
        put("para_id", "监测参数标识");
        put("ter_placeholder_id", "PLC序号");
        put("ter_para_id", "PLC变量标识");
        put("ter_para_name", "PLC变量名称");
        put("ter_para_addr", "PLC变量地址");
        put("ter_data_type", "数据类型");
        put("ter_addr_type", "地址类型");
        put("ter_endian", "大小端");
        put("group_num", "组合序号");
    }};

    public Map<String, String> terminalParam = new LinkedHashMap<String, String>() {{
        put("para_id", "PLC变量标识");
        put("para_name", "PLC变量名称");
        put("para_addr", "PLC变量地址");
        put("data_type", "数据类型");
        put("addr_type", "地址类型");
        put("endian", "大小端");
        put("group_num", "组合序号");
    }};
}
