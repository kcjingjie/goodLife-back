package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.SysDataRoleModel;
import com.youngc.pipeline.service.system.SysDataRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysDataRoleServiceImpl implements SysDataRoleService {

    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;

    public Page getDataRoleList(String dataName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)sysDataRoleMapper.getDataRoleList(dataName);
    }

    public SysDataRoleModel addDataRole(SysDataRoleModel sysDataRoleModel) {
        sysDataRoleMapper.insertDataRole(sysDataRoleModel);
        return sysDataRoleModel;
    }

    public SysDataRoleModel getDataRoleInfo(Long id) {
        return sysDataRoleMapper.getDataRoleInfo(id);
    }

    public SysDataRoleModel updateDataRoleInfo(SysDataRoleModel sysDataRoleModel) {
        sysDataRoleMapper.updateDataRoleInfo(sysDataRoleModel);
        return sysDataRoleModel;
    }

    public boolean deleteDataRoleList(String idList) {
        sysDataRoleMapper.deleteDataRole(idList);
        return true;
    }
}
