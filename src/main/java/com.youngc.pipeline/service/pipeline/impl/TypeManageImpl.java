package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.TypeManageMapper;
import com.youngc.pipeline.service.pipeline.TypeManageService;
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
