package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.SysDataRoleModel;

public interface SysDataRoleService {

    Page getDataRoleList(String dataName, int pageNum, int pageSize);

    SysDataRoleModel addDataRole(SysDataRoleModel sysDataRoleModel);

    SysDataRoleModel getDataRoleInfo(Long id);

    SysDataRoleModel updateDataRoleInfo(SysDataRoleModel sysDataRoleModel);

    boolean deleteDataRoleList(String idList);
}
