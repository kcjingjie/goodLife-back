package com.youngc.pipeline.service.maintenance;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevLeakModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DevLeakService {
    Page getList(String keyWord, String devName, int pageNum, int pageSize);

    DevLeakModel getInfo(Long id);

    DevLeakModel updateInfo(DevLeakModel devLeakModel);

    DevLeakModel insert(DevLeakModel devLeakModel);

    boolean delete(String idList);

    List getDevList();

    List<DevLeakModel> getInfoByNo(String leakNo);
}
