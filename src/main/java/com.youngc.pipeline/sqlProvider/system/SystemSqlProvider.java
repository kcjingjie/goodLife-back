package com.youngc.pipeline.sqlProvider.system;

import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author liweiqiang
 */
public class SystemSqlProvider {
    public String putDataUnit(Map<String, Object> para) {

        List<String> DataUnitId = (List<String>) para.get("arg0");
        Long userId = (Long) para.get("arg1");
        Long droleId = (Long) para.get("arg2");

        StringBuilder builder = new StringBuilder("insert into sys_data_role_unit (drole_id,unit_id,status,add_person,add_time,last_person,last_time) values ");

        for (int i = 0; i < DataUnitId.size(); i++) {
            System.out.println(DataUnitId.get(i));
            MessageFormat messageFormat = new MessageFormat("(" + droleId + "," + DataUnitId.get(i) + ", 1," + userId + ",now()," + userId + ",now())");
            builder.append(messageFormat.format(new Object[]{i + ""}));
            if (i < DataUnitId.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
