package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.PipeInfoModel;

public interface DevConfigParaService {
    Page getList( Long deviceId, int pageNum, int pageSize);

    DevConfigParaModel getInfo(Long id);

    DevConfigParaModel updateInfo(DevConfigParaModel devConfigParaModel);

    DevConfigParaModel insert(DevConfigParaModel devConfigParaModel);

    boolean delete(String idList);

    DevConfigParaModel getInfoByCode(Long deviceId,String paraId);
}
