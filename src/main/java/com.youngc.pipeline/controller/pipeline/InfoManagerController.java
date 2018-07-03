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

    /**
     * 获取单位树
     * @return
     */
    @GetMapping("/orgUnitTree")
    public Result getOrgUnitTree() {

        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.getOrgUnitTree());
    }

    /**
     * 分页获取设备信息
     * @param keyWord
     * @param pid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam String keyWord,@RequestParam Long pid, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(infoManagerService.getList(keyWord,pid,pageNum,pageSize));
    }

    /**
     * 添加设备信息
     * @param pipeInfoBean
     * @return
     */
    @PostMapping
    public Result post(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setUnitId(pipeInfoBean.getUnitId());
        pipeInfoModel.setModelId(pipeInfoBean.getModelId());
        pipeInfoModel.setImageId(pipeInfoBean.getImageId());
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

    /**
     * 根据设备id查询设备信息
     * @param deviceId
     * @return
     */
    @GetMapping("/{deviceId}")
    public Result getDetails(@PathVariable Long deviceId) {
        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.getInfo(deviceId));
    }

    /**
     * 修改设备信息
     */
    @PutMapping
    public Result put(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setDeviceId(pipeInfoBean.getDeviceId());
        pipeInfoModel.setModelId(pipeInfoBean.getModelId());
        pipeInfoModel.setImageId(pipeInfoBean.getImageId());
        pipeInfoModel.setDeviceAlias(pipeInfoBean.getDeviceAlias());
        pipeInfoModel.setDeviceName(pipeInfoBean.getDeviceName());
        pipeInfoModel.setStatus(pipeInfoBean.getStatus());
        pipeInfoModel.setAddress(pipeInfoBean.getAddress());
        pipeInfoModel.setDeviceDesc(pipeInfoBean.getDeviceDesc());

        pipeInfoModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.updateInfo(pipeInfoModel));
    }

    /**
     * 删除设备信息
     */
    @DeleteMapping(value = "/del")
    public Result delete(@RequestParam("idList") String idList) {
        infoManagerService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 查询设备编号是否唯一
     * @param code
     * @return
     */
    @GetMapping("/code")
    public Result getInfoByCode(@RequestParam String code) {
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getInfoByCode(code));
    }

    /**
     * 查询设备模型id，模型名称
     * @return
     */
    @GetMapping("/getModel")
    public Result getModel(){
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getDevModel());
    }
}
