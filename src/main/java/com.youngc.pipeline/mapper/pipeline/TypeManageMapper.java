package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.TypeManageModel;
import org.apache.ibatis.annotations.*;
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

    /**
     * 通过id查询类型
     * @param id
     * @return
     */
    @Select("SELECT model_id,model_code,model_name,model_type,model_equip,model_desc,status FROM dev_model"+
            " WHERE model_id=#{id}")
    TypeManageModel getTypeInfo(Long id);

    /**
     * 修改类型信息
     * @param typeManageModel
     * @return
     */
    @Update("UPDATE dev_model SET model_code=#{modelCode},model_name=#{modelName},model_type=#{modelType},model_equip=#{modelEquip},"+
    "model_desc=#{modelDesc},status=#{status},last_person=#{lastPerson},last_time=now() WHERE model_id=#{modelId}")
    int updateTypeInfo(TypeManageModel typeManageModel);

    /**
     * 添加类型信息
     * @param typeManageModel
     * @return
     */
    @Insert("INSERT INTO dev_model (model_code,model_name,model_type,model_equip,model_desc,status,add_person,add_time,last_person,last_time)"+
    " VALUES(#{modelCode},#{modelName},#{modelType},#{modelEquip},#{modelDesc},#{status},#{addPerson},now(),#{lastPerson},now())")
    int addType(TypeManageModel typeManageModel);

    /**
     * 删除类型信息
     * @param idList
     * @return
     */
    @Delete("DELETE FROM dev_model WHERE model_id IN (${idList})")
    int deleteTypeList(@Param("idList") String idList);

    /**
     * 通过编码查询类型
     * @param code
     * @return
     */
    @Select("SELECT model_id FROM dev_model WHERE model_code=#{code}")
    TypeManageModel getByCode(String code);

}
