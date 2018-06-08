package com.youngc.pipeline.sqlProvider.system;

import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @author liweiqiang
 */
public class SystemSqlProvider {
    /**
     * 添加数据角色关联单位
     *
     * @param para
     * @return
     */
    public String putDataUnit(Map<String, Object> para) {

        List<String> DataUnitId = (List<String>) para.get("arg0");
        Long userId = (Long) para.get("arg1");
        Long droleId = (Long) para.get("arg2");

        StringBuilder builder = new StringBuilder("INSERT INTO sys_data_role_unit (drole_id,unit_id,status,add_person,add_time,last_person,last_time) VALUES ");

        MessageFormat messageFormat = new MessageFormat("({0}, {1}, 1, {2}, now(), {3}, now())");

        for (int i = 0; i < DataUnitId.size(); i++) {
            builder.append(messageFormat.format(new Object[]{droleId, DataUnitId.get(i), userId, userId}));
            if (i < DataUnitId.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 添加权限关联模块信息
     *
     * @param para
     * @return
     */
    public String insertRoleModule(Map<String, Object> para) {

        List<String> moduleIds = (List<String>) para.get("arg0");
        Long roleId = (Long) para.get("arg1");
        Long userId = (Long) para.get("arg2");

        StringBuilder builder = new StringBuilder("INSERT INTO sys_role_module (role_id, module_id, status, add_person, add_time, last_person, last_time) VALUES ");

        MessageFormat messageFormat = new MessageFormat("({0}, {1}, 1, {2}, now(), {3}, now())");

        for (int i = 0; i < moduleIds.size(); i++) {
            builder.append(messageFormat.format(new Object[]{roleId, moduleIds.get(i), userId, userId}));
            if (i < moduleIds.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }


    /**
     * 给用户分配权限
     * @param para
     * @return
     */
    public String insertUserRole(Map<String, Object> para) {

        List<String> roleIds = (List<String>) para.get("arg0");
        Long userId = (Long) para.get("arg1");//需要修改的用户id
        Long personId = (Long) para.get("arg2");//操作用户的id

        StringBuilder builder = new StringBuilder("INSERT INTO sys_user_role (user_id, role_id, add_person, add_time, last_person, last_time) VALUES ");

        MessageFormat messageFormat = new MessageFormat("({0}, {1},{2}, now(), {3}, now())");

        for (int i = 0; i < roleIds.size(); i++) {
            builder.append(messageFormat.format(new Object[]{ roleIds.get(i),userId, personId, personId}));
            if (i < roleIds.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 给用户添加数据角色的权限
     * @param para
     * @return
     */
    public String insertUserDataRole(Map<String, Object> para) {

        List<String> droleIds = (List<String>) para.get("arg0");
        Long userId = (Long) para.get("arg1");//需要修改的用户id
        Long personId = (Long) para.get("arg2");//操作用户的id

        StringBuilder builder = new StringBuilder("INSERT INTO sys_user_data_role (user_id, drole_id, add_person, add_time, last_person, last_time) VALUES ");

        MessageFormat messageFormat = new MessageFormat("({0}, {1},{2}, now(), {3}, now())");

        for (int i = 0; i < droleIds.size(); i++) {
            builder.append(messageFormat.format(new Object[]{ droleIds.get(i),userId, personId, personId}));
            if (i < droleIds.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
}
