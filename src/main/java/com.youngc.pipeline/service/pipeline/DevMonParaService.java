package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevMonParaModel;

public interface DevMonParaService {
    Page getList(Long deviceId, int pageNum, int pageSize);

    DevMonParaModel getInfo(Long id);

    DevMonParaModel updateInfo(DevMonParaModel devMonParaModel);

    DevMonParaModel insert(DevMonParaModel devMonParaModel);

    boolean delete(String idList);

    DevMonParaModel getInfoByCode(Long deviceId,String paraId);
}
