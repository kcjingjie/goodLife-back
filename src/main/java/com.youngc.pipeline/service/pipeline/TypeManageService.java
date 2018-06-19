package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.TypeManageModel;

public interface TypeManageService {
    Page getList(String keyWord, int pageNum, int pageSize);
}
