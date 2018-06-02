package com.youngc.pipeline.service.system.impl;

import com.youngc.pipeline.bean.context.ModuleTreeNode;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.mapper.system.ModuleMapper;
import com.youngc.pipeline.model.ModuleModel;
import com.youngc.pipeline.service.system.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    private ModuleMapper moduleMapper;

    /**
     * 查询模块树
     */
    public List<TreeNode> getTree(String keyword) {

        List<Map> groups;

        List<Map> okGroups = new ArrayList<Map>();

        List<Map> noGroups = new ArrayList<Map>();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        groups = moduleMapper.getTree();

        for (Map map : groups) {
            if (((String) (map.get("module_name"))).contains(keyword)) {
                okGroups.add(map);
            } else {
                noGroups.add(map);
            }
        }
        for (int i = 0; i < okGroups.size(); i++) {
            if (((Integer) (okGroups.get(i).get("pid"))) != 0) {
                for (Map map1 : noGroups) {
                    if (okGroups.get(i).get("pid").equals(map1.get("module_id"))) {
                        if (!okGroups.contains(map1)) {
                            okGroups.add(map1);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < okGroups.size(); i++) {
            if (((Integer) (okGroups.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId(((Integer) (okGroups.get(i).get("module_id"))).toString());
                root.setName((String) (okGroups.get(i).get("module_name")));
                root.setChildren(getChilds(okGroups, ((Integer) (okGroups.get(i).get("module_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> groups, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (Map group : groups) {
            if ((group.get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId(((Integer) (group.get("module_id"))).toString());
                node.setName((String) (group.get("module_name")));
                node.setChildren(getChilds(groups, (Integer) (group.get("module_id"))));
                children.add(node);
            }
        }
        return children;
    }

    /**
     * 查询菜单树
     */
    public List<ModuleTreeNode> getModuleTree(String keyword) {
        List<Map> modules;


        List<ModuleTreeNode> tree = new ArrayList<ModuleTreeNode>();

        modules = moduleMapper.getTree();


        for (int i = 0; i < modules.size(); i++) {
            if (((Integer) (modules.get(i).get("pid"))) == 0) {
                ModuleTreeNode root = new ModuleTreeNode();
                root.setIcon(modules.get(i).get("icon").toString());
                root.setName((String) (modules.get(i).get("module_name")));
                root.setChildren(getModuleChilds(modules, (Integer) (modules.get(i).get("module_id"))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<ModuleTreeNode> getModuleChilds(List<Map> modules, Integer parentId) {
        List<ModuleTreeNode> children = new ArrayList<ModuleTreeNode>();
        for (Map module : modules) {
            if ((module.get("pid")).equals(parentId)) {
                ModuleTreeNode node = new ModuleTreeNode();
                node.setIcon(((module.get("icon"))).toString());
                node.setName((String) (module.get("module_name")));
                node.setChildren(getModuleChilds(modules, (Integer) (module.get("module_id"))));
                children.add(node);
            }
        }
        return children;
    }

    /**
     * 查询模块信息
     */
    public ModuleModel getModuleDetails(Long moduleId) {
        return moduleMapper.getModuleInfo(moduleId);
    }

    /**
     * 更新模块信息
     */
    public ModuleModel updateModuleDetails(ModuleModel moduleModel) {
        moduleMapper.update(moduleModel);
        return moduleModel;
    }

    /**
     * 添加模块信息
     */
    public ModuleModel addModule(ModuleModel moduleModel) {
        moduleMapper.addModule(moduleModel);
        return moduleModel;
    }

    /**
     * 删除模块
     */
    public void deleteModule(Long moduleId) {
        moduleMapper.deleteModule(moduleId);
    }

}
