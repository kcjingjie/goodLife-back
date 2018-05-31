package com.youngc.pipeline.mapper.system;

import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.DictionaryValueModel;
import com.youngc.pipeline.model.UserManagerModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DictionaryQueryMapper {

    /**
     * 数据字典操作sys_dictionary
     * @param keyword
     * @return
     */
    @Select(" SELECT * FROM sys_dictionary" +
            " WHERE ((dict_name LIKE CONCAT('%', #{keyword}, '%')) OR (dict_value LIKE CONCAT('%', #{keyword}, '%')))")
    List<DictionaryQueryModel> getList(String keyword);

    @Insert(" INSERT INTO sys_dictionary (dict_value, dict_name, status,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{dictValue}, #{dictName}, #{status},#{remark}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewDict(DictionaryQueryModel dictionaryQueryModel);

    @Delete(" DELETE FROM sys_dictionary WHERE id IN (${idList});")
    int deleteDictList(@Param("idList") String  idList);

    @Delete(" DELETE FROM sys_dict_data  WHERE " +
            " INSTR(CONCAT(',',(SELECT GROUP_CONCAT(dict_value separator ',') FROM sys_dictionary WHERE id IN (${idList})),',')," +
            " CONCAT(',',dict_value,','));")
    int deleteDictDataList(@Param("idList") String  idList);

    @Select(" SELECT * " +
            " FROM sys_dictionary WHERE id = #{id}")
    DictionaryQueryModel getDictInfo(@Param("id") Long id);

    @Update(" UPDATE sys_dictionary SET dict_value = #{dictValue}, dict_name = #{dictName},status=#{status},remark=#{remark}," +
            " last_person = #{lastPerson}, last_time = #{lastTime} WHERE id = #{id}")
    int updateDictInfo(DictionaryQueryModel dictionaryQueryModel);

    @Select(" SELECT * " +
            " FROM sys_dictionary WHERE dict_value = #{value}")
    DictionaryQueryModel getDictInfoByValue(@Param("value") String value);

    /**
     * 数据字典内容sys_dict_data操作
     */
    @Select(" SELECT * FROM sys_dict_data where dict_value=#{dictValue}" )
    List<DictionaryValueModel> getDictValueList(@Param("dictValue") String dictValue);

    @Select(" SELECT * " +
            " FROM sys_dict_data WHERE id = #{id}")
    DictionaryValueModel getDictValueInfo(@Param("id") Long id);

    @Update(" UPDATE sys_dict_data SET data_name = #{dataName}, data_value = #{dataValue},remark=#{remark}," +
            " last_person = #{lastPerson}, last_time = #{lastTime} WHERE id = #{id}")
    int updateDictValueInfo(DictionaryValueModel dictionaryValueModel);

    @Insert(" INSERT INTO sys_dict_data (data_name, data_value,dict_value,remark,add_person, add_time, last_person, last_time)" +
            " VALUES(#{dataName}, #{dataValue}, #{dictValue},#{remark}, #{addPerson}, #{addTime}, #{lastPerson}, #{lastTime})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertDictValue(DictionaryValueModel dictionaryValueModel);

    @Delete(" DELETE FROM sys_dict_data WHERE id IN (${idList})")
    int deleteDictValueList(@Param("idList") String  idList);

    @Select(" SELECT * " +
            " FROM sys_dict_data WHERE  dict_value = #{dictValue} and data_value=#{dataValue}")
    DictionaryValueModel getDictValueByValue(@Param("dictValue") String dictValue,@Param("dataValue") int dataValue);
}
