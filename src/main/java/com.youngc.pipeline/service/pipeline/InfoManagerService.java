package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.ImageModel;
import com.youngc.pipeline.model.PipeInfoModel;

import java.util.List;

public interface InfoManagerService {
     List<TreeNode> getOrgUnitTree();

    Page getList(String keyWord, Long pid, int pageNum, int pageSize);

    PipeInfoModel getInfo(Long id);

    PipeInfoModel updateInfo(PipeInfoModel pipeInfoModel);

    PipeInfoModel insert(PipeInfoModel pipeInfoModel);

    boolean delete(String unitIds);

    PipeInfoModel getInfoByCode(String code);

    List getDevModel();

    ImageModel getImageUrl(Long deviceId);
}
