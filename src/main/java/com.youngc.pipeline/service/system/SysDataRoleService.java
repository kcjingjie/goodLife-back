package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.DataUnitModel;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.SysDataRoleModel;

import java.util.List;

public interface SysDataRoleService {

    Page getDataRoleList(String dataName, int pageNum, int pageSize);

    SysDataRoleModel addDataRole(SysDataRoleModel sysDataRoleModel);

    SysDataRoleModel getDataRoleInfo(Long id);

    SysDataRoleModel updateDataRoleInfo(SysDataRoleModel sysDataRoleModel);

    boolean deleteDataRoleList(String idList);

    List<TreeNode> getOrgUnitTree();

    List<DataUnitModel> getDataUnit(Long droleId);
}
