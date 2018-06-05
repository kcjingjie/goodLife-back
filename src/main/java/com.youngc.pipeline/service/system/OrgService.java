package com.youngc.pipeline.service.system;

import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.OrgModel;

import java.util.List;

/**
 * @author liweiqiang
 */
public interface OrgService {
    /**
     * 查询模块树
     */
    List<TreeNode> getTree(String keyword);

    /**
     * 查询模块信息
     */
    OrgModel getOrg(Long userId);

    /**
     * 更新模块信息
     */
    OrgModel updateOrg(OrgModel orgModel);

    /**
     * 删除模块
     */
    void deleteOrg(Long userId);

    /**
     * 添加模块
     */
    OrgModel addOrg(OrgModel orgModel);
}
