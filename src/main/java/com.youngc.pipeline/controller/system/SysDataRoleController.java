package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.bean.param.DictionaryValueBean;
import com.youngc.pipeline.bean.param.SysDataRoleBean;
import com.youngc.pipeline.bean.param.UserBean;
import com.youngc.pipeline.model.DictionaryValueModel;
import com.youngc.pipeline.model.SysDataRoleModel;
import com.youngc.pipeline.model.UserManagerModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.SysDataRoleService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/dataRole")
public class SysDataRoleController {

    @Autowired
    private SysDataRoleService sysDataRoleService;

    /**
     * 通过角色名称模糊查询数据角色表中的数据
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getDataRoleList(@RequestParam String keyWord,@RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(sysDataRoleService.getDataRoleList(keyWord, pageNum, pageSize));
    }

    /**
     * 通过id查询数据角色表中的id
     * @param id
     * @return
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result getDataRoleInfo(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, sysDataRoleService.getDataRoleInfo(id));
    }

    /**
     * 添加数据角色
     * @param sysDataRoleBean
     * @return
     */
    @PostMapping(value = "/post")
    public Result postInfo(@RequestBody SysDataRoleBean sysDataRoleBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        SysDataRoleModel sysDataRoleModel = new SysDataRoleModel();

        sysDataRoleModel.setDroleName(sysDataRoleBean.getDroleName());
        sysDataRoleModel.setStatus(sysDataRoleBean.getStatus());
        sysDataRoleModel.setDroleDesc(sysDataRoleBean.getDroleDesc());

        sysDataRoleModel.setAddPerson(user.getUserId());
        sysDataRoleModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS,
                sysDataRoleService.addDataRole(sysDataRoleModel));
    }

    /**
     * 修改数据角色
     * @param sysDataRoleBean
     * @return
     */
    @PutMapping(value = "/put")
    public Result putDataRoleInfo(@RequestBody SysDataRoleBean sysDataRoleBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        SysDataRoleModel sysDataRoleModel = new SysDataRoleModel();

        sysDataRoleModel.setDroleId(sysDataRoleBean.getDroleId());
        sysDataRoleModel.setDroleName(sysDataRoleBean.getDroleName());
        sysDataRoleModel.setStatus(sysDataRoleBean.getStatus());
        sysDataRoleModel.setDroleDesc(sysDataRoleBean.getDroleDesc());

        sysDataRoleModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, sysDataRoleService.updateDataRoleInfo(sysDataRoleModel));
    }

    /**
     * 删除数据角色
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteList(@RequestParam("idList") String idList) {
        sysDataRoleService.deleteDataRoleList(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    @ApiOperation("获取组织单位树")
    @GetMapping("/orgUnitTree")
    public Result getOrgUnitTree() {

        return ResultGenerator.generate(ResultCode.SUCCESS, sysDataRoleService.getOrgUnitTree());
    }

}
