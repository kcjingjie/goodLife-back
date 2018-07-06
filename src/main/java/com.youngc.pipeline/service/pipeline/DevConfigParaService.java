package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.PipeInfoModel;

import java.util.List;

public interface DevConfigParaService {
    Page getList( Long deviceId, int pageNum, int pageSize);

    List getListAll(Long deviceId);

    DevConfigParaModel getInfo(Long id);

    DevConfigParaModel updateInfo(DevConfigParaModel devConfigParaModel);

    DevConfigParaModel insert(DevConfigParaModel devConfigParaModel);

    boolean delete(String idList);

   List<DevConfigParaModel> getInfoByCode(Long deviceId,String paraId);
}
