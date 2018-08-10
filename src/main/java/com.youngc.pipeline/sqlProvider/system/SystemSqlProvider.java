package com.youngc.pipeline.sqlProvider.system;

import com.youngc.pipeline.mapper.pipeline.DevUnitMapper;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevRepairModel;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.model.PipeInfoModel;
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
     *
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
            builder.append(messageFormat.format(new Object[]{userId, roleIds.get(i), personId, personId}));
            if (i < roleIds.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    /**
     * 给用户添加数据角色的权限
     *
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
            builder.append(messageFormat.format(new Object[]{userId, droleIds.get(i), personId, personId}));
            if (i < droleIds.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }
        /**
         * 添加设备管件信息
         * @param para
         * @return
         */
        public String readDevUnitExcel(Map<String, Object> para) {
            List<DevUnitModel> data = (List<DevUnitModel>) para.get("arg0");
            Long devUserId = (Long) para.get("arg1");
            Long devId = (Long) para.get("arg2");
            StringBuilder devBuilder = new StringBuilder("INSERT INTO dev_unit (device_id,unit_name,unit_version,  unit_number, unit_material,add_person,add_time,last_person, last_time) VALUES ");
            MessageFormat devMessageFormat = new MessageFormat("({0},{1},{2},{3},{4},{5},now(),{6},now())");
            for(int i=0;i<data.size();i++){
                DevUnitModel devUnitModel=data.get(i);
                devBuilder.append(devMessageFormat.format(new Object[]{devId,devUnitModel.getUnitName(),devUnitModel.getUnitVersion(),devUnitModel.getUnitNumber(),devUnitModel.getUnitMaterial(), devUserId, devUserId}));
                if (i < data.size() - 1) {
                    devBuilder.append(",");
                }
            }
            return devBuilder.toString();
        }

        /**
         * 添加设备管件备件
         * @param para
         * @return
         */
        public String readDevRepairExcel(Map<String, Object> para){
            List<DevRepairModel> devRepairModels=(List<DevRepairModel>) para.get("arg0");
            Long devUserId = (Long) para.get("arg1");
            Long devId = (Long) para.get("arg2");
            StringBuilder devBuilder = new StringBuilder("INSERT INTO dev_repair (device_id,manufactor,model,specification, material,company,brand,stock,quantity,cycle,price,add_person,add_time,last_person, last_time) VALUES ");
            MessageFormat devMessageFormat = new MessageFormat("({0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{11},now(),{12},now())");
            for(int i=0;i<devRepairModels.size();i++){
                DevRepairModel devRepairModel=devRepairModels.get(i);
                devBuilder.append(devMessageFormat.format(new Object[]{devId,devRepairModel.getManufactor(),devRepairModel.getModel(),devRepairModel.getSpecification(),
                        devRepairModel.getMaterial(),devRepairModel.getCompany(),devRepairModel.getBrand(),devRepairModel.getStock(),devRepairModel.getQuantity(),devRepairModel.getCycle(),devRepairModel.getPrice(),devUserId, devUserId}));
                if (i < devRepairModels.size() - 1) {
                    devBuilder.append(",");
                }
            }
            return devBuilder.toString();

        }

        /**
         * 导入管道信息
         * @param para
         * @return
         */
        public String addDevInfoByExcel1(Map<String,Object> para){
            List<PipeInfoModel> pipeInfoModels=(List<PipeInfoModel>) para.get("arg0");
            Long userId=(Long) para.get("arg1");
            Long unitId=(Long) para.get("arg2");
            StringBuilder devInfoBuilder = new StringBuilder("INSERT INTO dev_info (unit_id,device_name,pressure_pipe,device_equip,device_type,status,add_person,add_time,last_person, last_time) VALUES ");
            MessageFormat devMessageFormat = new MessageFormat("({0},{1},{2},(SELECT data_value FROM sys_dict_data WHERE dict_value=''device_equip'' AND data_name={3})," +
                    " (SELECT data_value FROM sys_dict_data WHERE dict_value=''deviceType'' AND data_name={4}),1,{5},now(),{6},now())");

            for(int i=0;i<pipeInfoModels.size();i++){
                PipeInfoModel pipeInfoModel=pipeInfoModels.get(i);
                devInfoBuilder.append(devMessageFormat.format(new Object[]{unitId,pipeInfoModel.getDeviceName(),pipeInfoModel.getPressurePipe(),
                    pipeInfoModel.getDeviceEquipName(),pipeInfoModel.getDeviceTypeName(),userId, userId}));
                if (i < pipeInfoModels.size() - 1) {
                    devInfoBuilder.append(",");
                }
            }
            return devInfoBuilder.toString();
        }

        public String addDevConfigParaByExcel(Map<String,Object> para){
            List<DevConfigParaModel> devConfigParaModels=(List<DevConfigParaModel>) para.get("arg0");
            Long userId=(Long) para.get("arg1");
            Long unitId=(Long) para.get("arg2");
            StringBuilder sqlBuilder = new StringBuilder(
                    " INSERT INTO dev_config_para(device_id, para_name," +
                            " para_value, para_type, "+
                            " add_person, add_time, last_person, last_time) VALUES");
            String template="((SELECT device_id FROM dev_info WHERE unit_id=%d AND device_name=%s),%s,%s,%d,%d,now(),%d,now())";
            for(int i=0;i<devConfigParaModels.size();i++){
                sqlBuilder.append(String.format(template,unitId,
                        devConfigParaModels.get(i).getDeviceName(),
                        devConfigParaModels.get(i).getParaName(),
                        devConfigParaModels.get(i).getParaValue(),
                        devConfigParaModels.get(i).getParaType(),
                        userId,
                        userId));
                if (i < devConfigParaModels.size() - 1) {
                    sqlBuilder.append(",");
                }
            }
            return sqlBuilder.toString();
        }
}
