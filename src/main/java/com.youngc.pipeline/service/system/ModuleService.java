package com.youngc.pipeline.service.system;

import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.ModuleModel;

import java.util.List;

/**
 * @author liweiqiang
 */
public interface ModuleService {
    /**
     * 查询模块树
     */
    List<TreeNode> getTree(String  keyword);

    /**
     * 查询模块信息
     */
    ModuleModel getModuleDetails(Long userId);

    /**
     * 更新模块信息
     */
    ModuleModel updateModuleDetails(ModuleModel moduleModel);

    /**
     * 删除模块
     */
    void deleteModule(Long userId);

    /**
     * 添加模块
     */
    ModuleModel addModule(ModuleModel moduleModel);
}
