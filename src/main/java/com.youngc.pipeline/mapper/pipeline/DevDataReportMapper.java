package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.DevDataReportModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DevDataReportMapper {

    /**
     * 根据模型划分查询设备个数
     * @return
     */
    @Select("SELECT model_name name,COUNT(device_id) number from dev_info " +
            "RIGHT  JOIN dev_model on dev_model.model_id=dev_info.model_id GROUP BY dev_model.model_id;")
    List<DevDataReportModel> getDevCountByModel();

    /**
     * 根据管道的类型查询设备个数
     * @return
     */
    @Select("SELECT sd.data_name name,COUNT(device_id) number from dev_info " +
            "RIGHT  JOIN dev_model on dev_model.model_id=dev_info.model_id " +
            "RIGHT JOIN (SELECT data_name ,data_value from sys_dict_data WHERE dict_value='deviceType')sd " +
            "ON sd.data_value=dev_model.model_type GROUP BY sd.data_value ;")
    List <DevDataReportModel> getDevConutByType();

}

