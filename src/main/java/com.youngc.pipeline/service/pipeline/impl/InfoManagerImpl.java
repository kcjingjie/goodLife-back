package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.mapper.pipeline.InfoManagerMapper;
import com.youngc.pipeline.mapper.system.OrgMapper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.model.TypeManageModel;
import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.service.pipeline.InfoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class InfoManagerImpl implements InfoManagerService{
    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private InfoManagerMapper infoManagerMapper;

    /**
     * 获取单位树
     * @return
     */
    public List<TreeNode> getOrgUnitTree() {

        List<Map> org = orgMapper.getTree();
        List<Map> unit = sysDataRoleMapper.getOrgUnitTree();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < org.size(); i++) {
            if (((Integer) (org.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId("org_" + ((Integer) (org.get(i).get("org_id"))).toString());
                root.setName((String) (org.get(i).get("org_name")));
                root.setChildren(getChilds(unit, org, ((Integer) (org.get(i).get("org_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> units, List<Map> orgs, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (int j = 0; j < orgs.size(); j++) {

            Iterator<Map> unit = units.iterator();

            while (unit.hasNext()) {
                Map unitValue = unit.next();
                TreeNode node = new TreeNode();
                if (unitValue.get("org_id").equals(parentId)) {
                    node.setId(((Integer) (unitValue.get("unit_id"))).toString());
                    node.setName((String) (unitValue.get("unit_name")));
                    children.add(node);
                    unit.remove();
                }
            }
            if ((orgs.get(j).get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId("org_" + ((Integer) (orgs.get(j).get("org_id"))).toString());
                node.setName((String) (orgs.get(j).get("org_name")));
                node.setChildren(getChilds(units, orgs, (Integer) (orgs.get(j).get("org_id"))));
                children.add(node);
            }

        }
        return children;
    }

    /**
     * 分页获取设备信息
     * @param keyWord
     * @param pid
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyWord,Long pid, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return (Page)infoManagerMapper.getList(keyWord,pid);
    }
    /**
     * 根据设备id查询设备信息
     * @param id
     * @return
     */
    public PipeInfoModel getInfo(Long id) {
        return infoManagerMapper.getInfo(id);
    }

    /**
     * 修改设备信息
     */
    public PipeInfoModel updateInfo(PipeInfoModel pipeInfoModel) {
        infoManagerMapper.updateInfo(pipeInfoModel);
        return pipeInfoModel;
    }

    /**
     * 添加设备信息
     * @param pipeInfoModel
     * @return
     */
    public PipeInfoModel insert(PipeInfoModel pipeInfoModel) {
        infoManagerMapper.insert(pipeInfoModel);
        return pipeInfoModel;
    }

    /**
     * 删除设备信息，同时删除设备下的监测参数与标准参数
     */
    @Transactional
    public boolean delete(String ids) {
        infoManagerMapper.delete(ids);
        infoManagerMapper.deleteConfigPara(ids);
        infoManagerMapper.deleteMonPara(ids);
        return true;
    }

    /**
     * 查询设备编号是否唯一
     * @param code
     * @return
     */
    public PipeInfoModel getInfoByCode(String code) {
        return infoManagerMapper.getInfoByCode(code);
    }

    /**
     * 查询设备模型id，模型名称
     * @return
     */
    public List getDevModel() {
        return infoManagerMapper.getDevModel();
    }


}
