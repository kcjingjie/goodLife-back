package com.youngc.pipeline.service.maintenance;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevInspectionModel;
import com.youngc.pipeline.model.PipeInfoModel;

import java.util.List;

public interface DevInspectionService {
    Page getList(String keyWord, String devName, int pageNum, int pageSize);

    DevInspectionModel getInfo(Long id);

    DevInspectionModel updateInfo(DevInspectionModel devInspectionModel);

    DevInspectionModel insert(DevInspectionModel devInspectionModel);

    boolean delete(String idList);

    List getDevList();
}
