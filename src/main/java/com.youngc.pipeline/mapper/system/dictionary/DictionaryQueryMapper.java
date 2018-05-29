package com.youngc.pipeline.mapper.system.dictionary;

import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryQueryMapper {

    @Select(" SELECT * FROM sys_dictionary" +
            " WHERE ((dict_name LIKE CONCAT('%', #{keyword}, '%')) OR (dict_value LIKE CONCAT('%', #{keyword}, '%')))")
    List<UserManagerModel> getList(String keyword);
}
