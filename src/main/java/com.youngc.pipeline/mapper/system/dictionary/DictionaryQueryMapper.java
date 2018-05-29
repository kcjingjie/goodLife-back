package com.youngc.pipeline.mapper.system.dictionary;

import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryQueryMapper {

    @Select(" SELECT * FROM sys_dictionary" +
            " WHERE ((dict_name LIKE CONCAT('%', #{keyword}, '%')) OR (dict_value LIKE CONCAT('%', #{keyword}, '%')))")
    List<DictionaryQueryModel> getList(String keyword);

    @Insert(" INSERT INTO sys_dictionary (dict_value, dict_name, status,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{dictValue}, #{dictName}, 0,#{remark}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewDict(DictionaryQueryModel dictionaryQueryModel);
}
