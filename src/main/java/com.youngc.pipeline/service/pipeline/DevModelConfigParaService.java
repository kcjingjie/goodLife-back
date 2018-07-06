package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevModelConfigParaModel;

import java.util.List;

public interface DevModelConfigParaService {
    Page getList(Long modelId, int pageNum, int pageSize);

    DevModelConfigParaModel getInfo(Long id);

    DevModelConfigParaModel updateInfo(DevModelConfigParaModel devModelConfigParaModel);

    DevModelConfigParaModel insert(DevModelConfigParaModel devModelConfigParaModel);

    boolean delete(String idList);

    List<DevModelConfigParaModel> getInfoByCode(Long modelId, String paraId);
}
