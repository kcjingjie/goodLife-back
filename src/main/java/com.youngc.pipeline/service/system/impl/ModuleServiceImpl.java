package com.youngc.pipeline.service.system.impl;

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
            if (((String) (map.get("group_name"))).contains(keyword)) {
                okGroups.add(map);
            } else {
                noGroups.add(map);
            }
        }
        for (int i = 0; i < okGroups.size(); i++) {
            if (((Integer) (okGroups.get(i).get("parent_id"))) != 0) {
                for (Map map1 : noGroups) {
                    if (okGroups.get(i).get("parent_id").equals(map1.get("group_id"))) {
                        if (!okGroups.contains(map1)) {
                            okGroups.add(map1);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < okGroups.size(); i++) {
            if (((Integer) (okGroups.get(i).get("parent_id"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId(((Integer) (okGroups.get(i).get("group_id"))).toString());
                root.setName((String) (okGroups.get(i).get("group_name")));
                root.setChildren(getChilds(okGroups, ((Integer) (okGroups.get(i).get("group_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> groups, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (Map group : groups) {
            if ((group.get("parent_id")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId(((Integer) (group.get("group_id"))).toString());
                node.setName((String) (group.get("group_name")));
                node.setChildren(getChilds(groups, (Integer) (group.get("group_id"))));
                children.add(node);
            }
        }
        return children;
    }
}
