package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.TypeManageMapper;
import com.youngc.pipeline.model.TypeManageModel;
import com.youngc.pipeline.service.pipeline.TypeManageService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeManageImpl implements TypeManageService {

    @Autowired
    TypeManageMapper typeManageMapper;

    /**
     * 查询类型
     *
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyWord, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) typeManageMapper.getList(keyWord);
    }

    /**
     * 通过id查询类型
     * @param id
     * @return
     */
    public TypeManageModel getTypeInfo(Long id){
        return typeManageMapper.getTypeInfo(id);
    }

    /**
     * 修改类型信息
     * @param typeManageModel
     * @return
     */
    public TypeManageModel updateTypeInfo(TypeManageModel typeManageModel){
        typeManageMapper.updateTypeInfo(typeManageModel);
        return typeManageModel;
    }

    /**
     * 添加类型信息
     * @param typeManageModel
     * @return
     */
    public TypeManageModel addType(TypeManageModel typeManageModel) {
        typeManageMapper.addType(typeManageModel);
        return typeManageModel;
    }

    /**
     * 删除类型信息,同时删除设备类型下的标准参数与监测参数
     * @param idList
     * @return
     */
    @Transactional
    public boolean deleteTypeList(String idList){
        typeManageMapper.deleteTypeList(idList);
        typeManageMapper.deleteConfigPara(idList);
        typeManageMapper.deleteMonPara(idList);
        return true;
    }

    /**
     * 通过编码查询类型
     * @param code
     * @return
     */
    public List<TypeManageModel> getByCode(String code){
        return typeManageMapper.getByCode(code);
    }
}
