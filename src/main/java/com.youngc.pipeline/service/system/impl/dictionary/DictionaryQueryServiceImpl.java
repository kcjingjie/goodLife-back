package com.youngc.pipeline.service.system.impl.dictionary;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.dictionary.DictionaryQueryMapper;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.service.system.dictionary.DictionaryQueryService;
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
}
