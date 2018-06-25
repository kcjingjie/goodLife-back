package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevConfigParaBean;
import com.youngc.pipeline.bean.param.PipeInfoBean;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.maintenance.DevCheckService;
import com.youngc.pipeline.service.pipeline.DevConfigParaService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devConfigPara")
public class DevConfigParaController {
    @Autowired
    private DevConfigParaService devConfigParaService;

    //模糊检索单位下的设备标准参数信息
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long deviceId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devConfigParaService.getList(deviceId,pageNum,pageSize));
    }

    //添加设备标准参数信息
    @PostMapping
    public Result post(@RequestBody DevConfigParaBean devConfigParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevConfigParaModel devConfigParaModel = new DevConfigParaModel();

        devConfigParaModel.setDeviceId(devConfigParaBean.getDeviceId());
        devConfigParaModel.setParaName(devConfigParaBean.getParaName());
        devConfigParaModel.setParaId(devConfigParaBean.getParaId());
        devConfigParaModel.setParaValue(devConfigParaBean.getParaValue());
        devConfigParaModel.setParaType(devConfigParaBean.getParaType());
        devConfigParaModel.setParaUnit(devConfigParaBean.getParaUnit());
        devConfigParaModel.setRemark(devConfigParaBean.getRemark());
        devConfigParaModel.setAddPerson(user.getUserId());
        devConfigParaModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devConfigParaService.insert(devConfigParaModel));
    }

    //根据设备id查询设备标准参数信息
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devConfigParaService.getInfo(id));
    }

    //修改设备标准参数信息
    @PutMapping
    public Result put(@RequestBody DevConfigParaBean devConfigParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevConfigParaModel devConfigParaModel = new DevConfigParaModel();

        devConfigParaModel.setId(devConfigParaBean.getId());
        devConfigParaModel.setDeviceId(devConfigParaBean.getDeviceId());
        devConfigParaModel.setParaName(devConfigParaBean.getParaName());
        devConfigParaModel.setParaId(devConfigParaBean.getParaId());
        devConfigParaModel.setParaType(devConfigParaBean.getParaType());
        devConfigParaModel.setParaValue(devConfigParaBean.getParaValue());
        devConfigParaModel.setParaUnit(devConfigParaBean.getParaUnit());
        devConfigParaModel.setRemark(devConfigParaBean.getRemark());

        devConfigParaModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, devConfigParaService.updateInfo(devConfigParaModel));
    }

    //删除设备标准参数信息
    @DeleteMapping(value = "/del")
    public Result delete(@RequestParam("idList") String idList) {
        devConfigParaService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
    //根据参数标识是否有重复
    @GetMapping("/code")
    public Result getInfoByCode(@RequestParam Long deviceId,@RequestParam String paraId) {
        return ResultGenerator.generate(ResultCode.SUCCESS,devConfigParaService.getInfoByCode(deviceId,paraId));
    }

}
