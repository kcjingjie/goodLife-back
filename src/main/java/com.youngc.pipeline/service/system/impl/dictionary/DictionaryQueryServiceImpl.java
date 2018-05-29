package com.youngc.pipeline.service.system.impl.dictionary;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.dictionary.DictionaryQueryMapper;
import com.youngc.pipeline.service.system.dictionary.DictionaryQueryService;
import org.springframework.stereotype.Service;

@Service
public class DictionaryQueryServiceImpl implements DictionaryQueryService {

    private DictionaryQueryMapper dictionaryQueryMapper;

    public Page getList(String dictName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)dictionaryQueryMapper.getList(dictName);
    }
}
