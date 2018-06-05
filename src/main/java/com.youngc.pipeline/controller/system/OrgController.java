package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.OrgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liweiqiang
 */
@RestController
@RequestMapping("/organization")
public class OrgController {
    @Autowired
    private OrgService orgService;

    @ApiOperation("获取模块树")
    @GetMapping("/tree")
    public Result getTree(@RequestParam String keyword) {

        return ResultGenerator.generate(ResultCode.SUCCESS,orgService.getTree(keyword));
    }

    @ApiOperation("获取模块信息")
    @GetMapping("/{orgId}")
    public Result getOrgInfo(@PathVariable Long orgId) {

        return ResultGenerator.generate(ResultCode.SUCCESS,orgService.getOrg(orgId));
    }

//    @ApiOperation("修改模块信息")
//    @PutMapping
//    public Result getOrgInfo(@RequestBody OrgBean orgBean) {
//        UserBean user = (UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
//
//        ModuleModel moduleModel = new ModuleModel();
//        moduleModel.setModuleName(orgBean.getModuleName());
//        moduleModel.setIcon(orgBean.getIcon());
//        moduleModel.setControlId(orgBean.getControlId());
//        moduleModel.setType(orgBean.getType());
//        moduleModel.setStatus(orgBean.getStatus());
//        moduleModel.setModulePath(orgBean.getModulePath());
//        moduleModel.setPid(moduleBean.getPid());
//        moduleModel.setPriority(moduleBean.getPriority());
//        moduleModel.setModuleDesc(moduleBean.getModuleDesc());
//        moduleModel.setModuleId(moduleBean.getModuleId());
//
//        moduleModel.setLastPerson(user.getUserId());
//
//        return ResultGenerator.generate(ResultCode.SUCCESS,moduleService.updateModuleDetails(moduleModel));
//    }
//
//    @ApiOperation("添加模块信息")
//    @PostMapping
//    public Result postOrgInfo(@RequestBody ModuleBean moduleBean) {
//        UserBean user = (UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
//
//        ModuleModel moduleModel = new ModuleModel();
//
//        moduleModel.setModuleName(moduleBean.getModuleName());
//        moduleModel.setIcon(moduleBean.getIcon());
//        moduleModel.setControlId(moduleBean.getControlId());
//        moduleModel.setType(moduleBean.getType());
//        moduleModel.setStatus(moduleBean.getStatus());
//        moduleModel.setModulePath(moduleBean.getModulePath());
//        moduleModel.setPid(moduleBean.getModuleId());
//        moduleModel.setPriority(moduleBean.getPriority());
//        moduleModel.setModuleDesc(moduleBean.getModuleDesc());
//
//        moduleModel.setAddPerson(user.getUserId());
//        moduleModel.setLastPerson(user.getUserId());
//
//        return ResultGenerator.generate(ResultCode.SUCCESS,moduleService.addModule(moduleModel));
//    }
//
//    @ApiOperation("删除模块信息")
//    @DeleteMapping
//    public Result deleteOrg(@RequestParam Long moduleId) {
//        moduleService.deleteModule(moduleId);
//        return ResultGenerator.generate(ResultCode.SUCCESS);
//    }
}
