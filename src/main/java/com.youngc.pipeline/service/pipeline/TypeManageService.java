package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.TypeManageModel;

public interface TypeManageService {
    Page getList(String keyWord, int pageNum, int pageSize);

    TypeManageModel getTypeInfo(Long id);

    TypeManageModel updateTypeInfo(TypeManageModel typeManageModel);

    TypeManageModel addType(TypeManageModel typeManageModel);

    boolean  deleteTypeList(String idList);

    TypeManageModel getByCode(String code);
}
