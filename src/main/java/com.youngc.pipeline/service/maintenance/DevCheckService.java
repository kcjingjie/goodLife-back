package com.youngc.pipeline.service.maintenance;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevCheckModel;

public interface DevCheckService {
    Page getList( String devName, int pageNum, int pageSize);

    DevCheckModel getInfo(Long id);

    DevCheckModel updateInfo(DevCheckModel devCheckModel);

    DevCheckModel insert(DevCheckModel devCheckModel);

    boolean delete(String idList);

    DevCheckModel submitInfo(DevCheckModel devCheckModel);
}

