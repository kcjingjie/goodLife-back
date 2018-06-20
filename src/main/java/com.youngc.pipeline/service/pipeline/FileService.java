package com.youngc.pipeline.service.pipeline;


import com.youngc.pipeline.model.FileModel;

import java.util.List;

/**
 * @author liweiqiang
 */
public interface FileService {
    /**
     * 查询模块信息
     */
    List<FileModel> getFileInfo(String orgId, String unitId);

}
