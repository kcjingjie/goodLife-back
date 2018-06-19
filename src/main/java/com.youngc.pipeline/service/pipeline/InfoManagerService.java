package com.youngc.pipeline.service.pipeline;

import com.github.pagehelper.Page;
import com.youngc.pipeline.bean.context.TreeNode;

import java.util.List;

public interface InfoManagerService {
     List<TreeNode> getOrgUnitTree();

    Page getList(String keyWord, Long pid, int pageNum, int pageSize);
}
