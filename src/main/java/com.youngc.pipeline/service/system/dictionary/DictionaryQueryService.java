package com.youngc.pipeline.service.system.dictionary;

import com.github.pagehelper.Page;

public interface DictionaryQueryService {

    Page getList(String dictName, int pageNum, int pageSize);
}
