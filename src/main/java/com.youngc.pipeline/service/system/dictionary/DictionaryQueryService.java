package com.youngc.pipeline.service.system.dictionary;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DictionaryQueryModel;

public interface DictionaryQueryService {

    Page getList(String dictName, int pageNum, int pageSize);

    DictionaryQueryModel addDict(DictionaryQueryModel dictionaryQueryModel);
}
