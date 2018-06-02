package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.SysRoleMapper;
import com.youngc.pipeline.model.SysRoleModel;
import com.youngc.pipeline.service.system.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 分页获取角色信息
     * @param dataName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getRoleList(String dataName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page) sysRoleMapper.getRoleList(dataName);
    }

    /**
     * 添加角色
     * @param sysRoleModel
     * @return
     */
    public SysRoleModel addRole(SysRoleModel sysRoleModel) {
        sysRoleMapper.insertRole(sysRoleModel);
        return sysRoleModel;
    }

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    public SysRoleModel getRoleInfo(Long id) {
        return sysRoleMapper.getRoleInfo(id);
    }

    /**
     * 更新角色信息
     * @param sysRoleModel
     * @return
     */
    public SysRoleModel updateRoleInfo(SysRoleModel sysRoleModel) {
        sysRoleMapper.updateRoleInfo(sysRoleModel);
        return sysRoleModel;
    }

    /**
     * 删除角色
     * @param idList
     * @return
     */
    public boolean deleteRoleList(String idList) {
        sysRoleMapper.deleteRole(idList);
        return true;
    }
}
