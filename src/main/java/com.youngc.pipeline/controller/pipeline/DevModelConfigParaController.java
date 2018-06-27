package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevConfigParaBean;
import com.youngc.pipeline.bean.param.DevModelConfigParaBean;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevModelConfigParaModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevModelConfigParaService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devModelConfig")
public class DevModelConfigParaController {
    @Autowired
    private DevModelConfigParaService devModelConfigParaService;

    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long modelId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devModelConfigParaService.getList(modelId,pageNum,pageSize));
    }

    //添加设备标准参数信息
    @PostMapping
    public Result post(@RequestBody DevModelConfigParaBean devModelConfigParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevModelConfigParaModel devModelConfigParaModel = new DevModelConfigParaModel();

        devModelConfigParaModel.setModelId(devModelConfigParaBean.getModelId());
        devModelConfigParaModel.setParaName(devModelConfigParaBean.getParaName());
        devModelConfigParaModel.setParaId(devModelConfigParaBean.getParaId());
        devModelConfigParaModel.setParaValue(devModelConfigParaBean.getParaValue());
        devModelConfigParaModel.setParaType(devModelConfigParaBean.getParaType());
        devModelConfigParaModel.setParaUnit(devModelConfigParaBean.getParaUnit());
        devModelConfigParaModel.setRemark(devModelConfigParaBean.getRemark());
        devModelConfigParaModel.setAddPerson(user.getUserId());
        devModelConfigParaModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devModelConfigParaService.insert(devModelConfigParaModel));
    }

    //根据设备id查询设备标准参数信息
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devModelConfigParaService.getInfo(id));
    }

    //修改设备标准参数信息
    @PutMapping
    public Result put(@RequestBody DevModelConfigParaBean devModelConfigParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevModelConfigParaModel devModelConfigParaModel = new DevModelConfigParaModel();

        devModelConfigParaModel.setId(devModelConfigParaBean.getId());
        devModelConfigParaModel.setModelId(devModelConfigParaBean.getModelId());
        devModelConfigParaModel.setParaName(devModelConfigParaBean.getParaName());
        devModelConfigParaModel.setParaId(devModelConfigParaBean.getParaId());
        devModelConfigParaModel.setParaValue(devModelConfigParaBean.getParaValue());
        devModelConfigParaModel.setParaType(devModelConfigParaBean.getParaType());
        devModelConfigParaModel.setParaUnit(devModelConfigParaBean.getParaUnit());
        devModelConfigParaModel.setRemark(devModelConfigParaBean.getRemark());

        devModelConfigParaModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, devModelConfigParaService.updateInfo(devModelConfigParaModel));
    }

    //删除设备标准参数信息
    @DeleteMapping(value = "/del")
    public Result delete(@RequestParam("idList") String idList) {
        devModelConfigParaService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
    //根据参数标识是否有重复
    @GetMapping("/code")
    public Result getInfoByCode(@RequestParam Long modelId,@RequestParam String paraId) {
        return ResultGenerator.generate(ResultCode.SUCCESS,devModelConfigParaService.getInfoByCode(modelId,paraId));
    }
}
