package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.DevRepairModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DevRepairService {
    Page getList(Long deviceId, int pageNum, int pageSize);

    DevRepairModel getInfo(Long id);

    DevRepairModel updateInfo(DevRepairModel devConfigParaModel);

    DevRepairModel insert(DevRepairModel devConfigParaModel);

    boolean delete(String idList);

    boolean readExcel(Long devId,MultipartFile file);

    String downloadFileInfo(HttpServletRequest request, HttpServletResponse response,Long devId);
}
