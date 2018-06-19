package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.TypeManageModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TypeManageMapper {

    /**
     * 查询类型
     * @param keyWord
     * @return
     */
    @Select("SELECT model_id,model_code,model_name,model_type,model_equip,model_desc,status FROM dev_model"+
    " WHERE ((model_name LIKE CONCAT('%', #{keyword}, '%'))OR(model_code LIKE CONCAT('%', #{keyword}, '%')))")
    List<TypeManageModel> getList(String keyWord);
}
