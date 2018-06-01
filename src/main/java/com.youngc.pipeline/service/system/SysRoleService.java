package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.SysDataRoleModel;
import com.youngc.pipeline.model.SysRoleModel;

public interface SysRoleService {
    Page getRoleList(String dataName, int pageNum, int pageSize);

    SysRoleModel addRole(SysRoleModel sysRoleModel);

    SysRoleModel getRoleInfo(Long id);

    SysRoleModel updateRoleInfo(SysRoleModel sysRoleModel);

    boolean deleteRoleList(String idList);
}
