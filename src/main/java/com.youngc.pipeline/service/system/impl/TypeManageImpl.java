package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.TypeManageMapper;
import com.youngc.pipeline.model.TypeManageModel;
import com.youngc.pipeline.service.system.TypeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeManageImpl implements TypeManageService{

    @Autowired
    TypeManageMapper typeManageMapper;

    /**
     * 查询类型
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyWord, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return (Page)typeManageMapper.getList(keyWord);
    }
}
