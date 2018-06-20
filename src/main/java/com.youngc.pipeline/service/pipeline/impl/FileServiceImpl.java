package com.youngc.pipeline.service.pipeline.impl;


import com.youngc.pipeline.mapper.pipeline.FileMapper;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.service.pipeline.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    /**
     * 查询文件信息
     */
    public List<FileModel> getFileInfo(String orgId, String unitId) {
        String deviceIds = "";

        if (orgId.equals("0")) {
            deviceIds = fileMapper.getDevIdByUnitId(unitId);

        } else {
            String[] orgIdArr = orgId.split("_");
            Long org = Long.parseLong(orgIdArr[1]);
            String unitIds = fileMapper.getUnitIdByOrgId(org);
            if (unitIds != null) {
                deviceIds = fileMapper.getDevIdByUnitId(unitIds);
            }
        }
        if (deviceIds != null) {
            return fileMapper.getFileInfoByDevId(deviceIds);
        } else {
            return null;
        }

    }
}
