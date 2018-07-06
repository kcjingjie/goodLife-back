package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevConfigParaBean;
import com.youngc.pipeline.bean.param.DevMonParaBean;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.model.DevMonParaModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevMonParaService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devMonPara")
public class DevMonParaController {

    @Autowired
    private DevMonParaService devMonParaService;

    /**
     * 分页获取设备监测参数信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long deviceId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devMonParaService.getList(deviceId,pageNum,pageSize));
    }

    /**
     * 查询设备所有监测参数信息
     * @param deviceId
     * @return
     */
    @GetMapping(value = "/getListAll")
    public Result getListAll(@RequestParam Long deviceId){
        return ResultGenerator.generate(ResultCode.SUCCESS,devMonParaService.getListAll(deviceId));
    }
    /**
     * 添加设备监测参数信息
     * @param devMonParaBean
     * @return
     */
    @PostMapping
    public Result postInfo(@RequestBody DevMonParaBean devMonParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevMonParaModel devMonParaModel = new DevMonParaModel();

        devMonParaModel.setDeviceId(devMonParaBean.getDeviceId());
        devMonParaModel.setParaName(devMonParaBean.getParaName());
        devMonParaModel.setParaId(devMonParaBean.getParaId());
        devMonParaModel.setParaDataType(devMonParaBean.getParaDataType());
        devMonParaModel.setParaType(devMonParaBean.getParaType());
        devMonParaModel.setParaUnit(devMonParaBean.getParaUnit());
        devMonParaModel.setRemark(devMonParaBean.getRemark());
        devMonParaModel.setAddPerson(user.getUserId());
        devMonParaModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devMonParaService.insert(devMonParaModel));
    }

    /**
     * 根据id获取设备监测参数信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devMonParaService.getInfo(id));
    }

    /**
     * 修改设备监测参数信息
     * @param devMonParaBean
     * @return
     */
    @PutMapping
    public Result putInfo(@RequestBody DevMonParaBean devMonParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevMonParaModel devMonParaModel = new DevMonParaModel();

        devMonParaModel.setId(devMonParaBean.getId());
        devMonParaModel.setDeviceId(devMonParaBean.getDeviceId());
        devMonParaModel.setParaName(devMonParaBean.getParaName());
        devMonParaModel.setParaId(devMonParaBean.getParaId());
        devMonParaModel.setParaDataType(devMonParaBean.getParaDataType());
        devMonParaModel.setParaType(devMonParaBean.getParaType());
        devMonParaModel.setParaUnit(devMonParaBean.getParaUnit());
        devMonParaModel.setRemark(devMonParaBean.getRemark());

        devMonParaModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, devMonParaService.updateInfo(devMonParaModel));
    }

    /**
     * 删除设备监测参数信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devMonParaService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 查询设备监测参数的标识是否唯一
     * @param deviceId
     * @param paraId
     * @return
     */
    @GetMapping("/code")
    public Result getInfoByCode(@RequestParam Long deviceId,@RequestParam String paraId) {
        return ResultGenerator.generate(ResultCode.SUCCESS,devMonParaService.getInfoByCode(deviceId,paraId));
    }
}
