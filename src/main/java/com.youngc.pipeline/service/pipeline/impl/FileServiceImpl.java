package com.youngc.pipeline.service.pipeline.impl;


import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.mapper.pipeline.FileMapper;
import com.youngc.pipeline.mapper.system.OrgMapper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.service.pipeline.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;

    /**
     * 查询组织单位设备树
     */
    public List<TreeNode> getOrgUnitTree() {

        List<Map> org = orgMapper.getTree();
        List<Map> unit = sysDataRoleMapper.getOrgUnitTree();
        List<Map> dev = fileMapper.getDevTree();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < org.size(); i++) {
            if (((Integer) (org.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId("org_" + ((Integer) (org.get(i).get("org_id"))).toString());
                root.setName((String) (org.get(i).get("org_name")));
                root.setChildren(getChilds(unit, org, dev, ((Integer) (org.get(i).get("org_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> units, List<Map> orgs, List<Map> dev, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (int j = 0; j < orgs.size(); j++) {

            Iterator<Map> unit = units.iterator();

            while (unit.hasNext()) {
                Map unitValue = unit.next();
                TreeNode node = new TreeNode();
                if (unitValue.get("org_id").equals(parentId)) {
                    node.setId("unit_" + ((Integer) (unitValue.get("unit_id"))).toString());
                    node.setName((String) (unitValue.get("unit_name")));
                    node.setChildren(getUnitChilds(dev, (Integer) (unitValue.get("unit_id"))));
                    children.add(node);
                    unit.remove();
                }
            }
            if ((orgs.get(j).get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId("org_" + ((Integer) (orgs.get(j).get("org_id"))).toString());
                node.setName((String) (orgs.get(j).get("org_name")));
                node.setChildren(getChilds(units, orgs, dev, (Integer) (orgs.get(j).get("org_id"))));
                children.add(node);
            }

        }
        return children;
    }

    List<TreeNode> getUnitChilds(List<Map> dev, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        Iterator<Map> devs = dev.iterator();

        while (devs.hasNext()) {
            Map devValue = devs.next();
            TreeNode node = new TreeNode();
            if (devValue.get("unit_id").equals(parentId)) {
                node.setId("dev_" + ((Integer) (devValue.get("device_id"))).toString());
                node.setName((String) (devValue.get("device_name")));
                children.add(node);
                devs.remove();
            }
        }
        return children;
    }

    /**
     * 查询文件信息
     */
    public List<FileModel> getFileInfo(String orgId, String unitId, String devId) {
        String deviceIds = "";

        if (orgId.equals("0") && unitId.equals("0")) {
            String[] devIdArr = devId.split("_");
            deviceIds = devIdArr[1];

        } else if (orgId.equals("0") && devId.equals("0")) {
            String[] unitIdArr = unitId.split("_");
            deviceIds = fileMapper.getDevIdByUnitId(unitIdArr[1]);

        } else {
            String[] orgIdArr = orgId.split("_");
            Long org = Long.parseLong(orgIdArr[1]);
            String unitIds = fileMapper.getUnitIdByOrgId(org);
            if (unitIds != null) {
                deviceIds = fileMapper.getDevIdByUnitId(unitIds);
            }
        }
        if (deviceIds != null && deviceIds != "") {
            return fileMapper.getFileInfoByDevId(deviceIds);
        } else {
            return null;
        }
    }

    /**
     * 添加文件夹信息
     */
    public boolean addfolder(FileModel fileModel) {
        fileMapper.postFolder(fileModel);
        return true;
    }

    /**
     * 删除档案信息
     */
    public boolean deleteFileInfo(String fileId,  String type) {
        fileMapper.deleteFile(fileId);
        if(Integer.parseInt(type) !=3){

        }
        return true;
    }
    /**
     * 查询文件夹下文件信息
     */
    public List<FileModel> getFolderFileInfo(Long fileId){
        return fileMapper.getFolderFileInfo(fileId);
    }
}
