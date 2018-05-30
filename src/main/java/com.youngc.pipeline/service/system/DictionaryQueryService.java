package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.UserManagerModel;

public interface DictionaryQueryService {

    Page getList(String dictName, int pageNum, int pageSize);

    DictionaryQueryModel addDict(DictionaryQueryModel dictionaryQueryModel);

    DictionaryQueryModel getDictInfo(Long id);

    DictionaryQueryModel updateDictInfo(DictionaryQueryModel dictionaryQueryModel);

    boolean deleteDictList(String idList);

    DictionaryQueryModel getDictInfoByValue(String value);
}
