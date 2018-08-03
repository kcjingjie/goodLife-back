package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevRepairModel;

public interface DevRepairService {
    Page getList(Long deviceId, int pageNum, int pageSize);

    DevRepairModel getInfo(Long id);

    DevRepairModel updateInfo(DevRepairModel devConfigParaModel);

    DevRepairModel insert(DevRepairModel devConfigParaModel);

    boolean delete(String idList);
}
