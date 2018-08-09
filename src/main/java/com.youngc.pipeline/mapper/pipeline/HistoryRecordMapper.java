package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.HistoryRecordModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author  liuyan
 */
@Component
public interface HistoryRecordMapper {

    @Select(" SELECT id,add_person,device_id , description,add_time FROM dev_check_history")
    List<HistoryRecordModel> getList();


}
