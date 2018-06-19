package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.PipeInfoBean;
import com.youngc.pipeline.bean.param.UnitBean;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.InfoManagerService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pipeInfo")
public class InfoManagerController {

    @Autowired
    private InfoManagerService infoManagerService;

    @GetMapping("/orgUnitTree")
    public Result getOrgUnitTree() {

        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.getOrgUnitTree());
    }

    //模糊检索单位下的设备信息
    @GetMapping(value = "/getList")
    public Result getDictionaryList(@RequestParam String keyWord,@RequestParam Long pid, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(infoManagerService.getList(keyWord,pid,pageNum,pageSize));
    }

    //添加设备信息
    @PostMapping
    public Result postUser(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setUnitId(pipeInfoBean.getUnitId());
        pipeInfoModel.setModelId(pipeInfoBean.getModelId());
        pipeInfoModel.setDeviceAlias(pipeInfoBean.getDeviceAlias());
        pipeInfoModel.setDeviceName(pipeInfoBean.getDeviceName());
        pipeInfoModel.setDeviceCode(pipeInfoBean.getDeviceCode());
        pipeInfoModel.setStatus(pipeInfoBean.getStatus());
        pipeInfoModel.setAddress(pipeInfoBean.getAddress());
        pipeInfoModel.setDeviceDesc(pipeInfoBean.getDeviceDesc());

        pipeInfoModel.setAddPerson(user.getUserId());
        pipeInfoModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.insert(pipeInfoModel));
    }

    //根据设备id查询设备信息
    @GetMapping("/{deviceId}")
    public Result getUnitDetails(@PathVariable Long deviceId) {
        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.getInfo(deviceId));
    }

    //修改设备信息
    @PutMapping
    public Result putUser(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setModelId(pipeInfoBean.getModelId());
        pipeInfoModel.setDeviceAlias(pipeInfoBean.getDeviceAlias());
        pipeInfoModel.setDeviceName(pipeInfoBean.getDeviceName());
        pipeInfoModel.setStatus(pipeInfoBean.getStatus());
        pipeInfoModel.setAddress(pipeInfoBean.getAddress());
        pipeInfoModel.setDeviceDesc(pipeInfoBean.getDeviceDesc());

        pipeInfoModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.updateInfo(pipeInfoModel));
    }

    //根据设备编号查询是否有重复
    @GetMapping("/code")
    public Result getUnitByCode(@RequestParam String code) {
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getInfoByCode(code));
    }
}
