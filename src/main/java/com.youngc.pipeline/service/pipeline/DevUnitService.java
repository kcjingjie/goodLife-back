package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevUnitModel;

public interface DevUnitService {

    Page getList(Long deviceId, int pageNum, int pageSize);

    DevUnitModel getInfo(Long id);

    DevUnitModel updateInfo(DevUnitModel devUnitModel);

    DevUnitModel insert(DevUnitModel devUnitModel);

    boolean delete(String idList);
}
