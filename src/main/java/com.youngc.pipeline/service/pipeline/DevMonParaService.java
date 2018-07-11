package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevMonParaModel;

import java.util.List;

public interface DevMonParaService {
    Page getList(Long deviceId, int pageNum, int pageSize);

    List getListAll(Long deviceId);

    DevMonParaModel getInfo(Long id);

    DevMonParaModel updateInfo(DevMonParaModel devMonParaModel);

    DevMonParaModel insert(DevMonParaModel devMonParaModel);

    boolean delete(String idList);

    List<DevMonParaModel> getInfoByCode(Long deviceId,String paraId);
}
