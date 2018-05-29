package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.DictionaryQueryMapper;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.service.system.DictionaryQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryQueryServiceImpl implements DictionaryQueryService {

    @Autowired
    private DictionaryQueryMapper dictionaryQueryMapper;

    public Page getList(String dictName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)dictionaryQueryMapper.getList(dictName);
    }

    public DictionaryQueryModel addDict(DictionaryQueryModel dictionaryQueryModel) {
        dictionaryQueryMapper.insertNewDict(dictionaryQueryModel);
        return dictionaryQueryModel;
    }

    public DictionaryQueryModel getDictInfo(Long id) {
        return dictionaryQueryMapper.getDictInfo(id);
    }

    public DictionaryQueryModel updateDictInfo(DictionaryQueryModel dictionaryQueryModel) {
        dictionaryQueryMapper.updateDictInfo(dictionaryQueryModel);
        return dictionaryQueryModel;
    }

    public boolean deleteDictList(String idList) {
        dictionaryQueryMapper.deleteDictList(idList);
        return true;
    }
}
