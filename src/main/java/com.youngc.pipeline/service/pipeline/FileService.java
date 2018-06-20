package com.youngc.pipeline.service.pipeline;


import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.FileModel;

import java.util.List;

/**
 * @author liweiqiang
 */
public interface FileService {

    List<TreeNode> getOrgUnitTree();

    List<FileModel> getFileInfo(String orgId, String unitId, String devId);

    boolean addfolder(FileModel fileModel);

    boolean deleteFileInfo(String fileId,  String type);

    List<FileModel> getFolderFileInfo(Long fileId);
}
