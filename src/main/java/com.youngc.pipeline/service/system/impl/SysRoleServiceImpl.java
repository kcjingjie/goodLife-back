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

    public Page getRoleList(String dataName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page) sysRoleMapper.getRoleList(dataName);
    }

    public SysRoleModel addRole(SysRoleModel sysRoleModel) {
        sysRoleMapper.insertRole(sysRoleModel);
        return sysRoleModel;
    }

    public SysRoleModel getRoleInfo(Long id) {
        return sysRoleMapper.getRoleInfo(id);
    }

    public SysRoleModel updateRoleInfo(SysRoleModel sysRoleModel) {
        sysRoleMapper.updateRoleInfo(sysRoleModel);
        return sysRoleModel;
    }

    public boolean deleteRoleList(String idList) {
        sysRoleMapper.deleteRole(idList);
        return true;
    }
}
