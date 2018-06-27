package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevModelConfigParaModel;
import com.youngc.pipeline.model.DevModelMonParaModel;

public interface DevModelMonParaService {
    Page getList(Long modelId, int pageNum, int pageSize);

    DevModelMonParaModel getInfo(Long id);

    DevModelMonParaModel updateInfo(DevModelMonParaModel devModelMonParaModel);

    DevModelMonParaModel insert(DevModelMonParaModel devModelMonParaModel);

    boolean delete(String idList);

    DevModelMonParaModel getInfoByCode(Long modelId,String paraId);
}
