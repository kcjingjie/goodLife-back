package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevUnitBean;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevUnitService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devUnit")
public class DevUnitController {

    @Autowired
    private DevUnitService devUnitService;

    /**
     * 分页获取设备标准参数信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long deviceId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devUnitService.getList(deviceId,pageNum,pageSize));
    }

    /**
     * 添加设备标准参数信息
     * @param devUnitBean
     * @return
     */
    @PostMapping
    public Result postInfo(@RequestBody DevUnitBean devUnitBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevUnitModel devUnitModel = new DevUnitModel();

        devUnitModel.setDeviceId(devUnitBean.getDeviceId());
        devUnitModel.setUnitName(devUnitBean.getUnitName());
        devUnitModel.setUnitVersion(devUnitBean.getUnitVersion());
        devUnitModel.setUnitNumber(devUnitBean.getUnitNumber());
        devUnitModel.setUnitMaterial(devUnitBean.getUnitMaterial());
        devUnitModel.setRemark(devUnitBean.getRemark());
        devUnitModel.setAddPerson(user.getUserId());
        devUnitModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devUnitService.insert(devUnitModel));
    }

    /**
     * 根据id查询设备标准参数信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devUnitService.getInfo(id));
    }

    /**
     * 修改设备标准参数信息
     * @param devUnitBean
     * @return
     */
    @PutMapping
    public Result putInfo(@RequestBody DevUnitBean devUnitBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevUnitModel devUnitModel = new DevUnitModel();

        devUnitModel.setId(devUnitBean.getId());
        devUnitModel.setDeviceId(devUnitBean.getDeviceId());
        devUnitModel.setUnitName(devUnitBean.getUnitName());
        devUnitModel.setUnitVersion(devUnitBean.getUnitVersion());
        devUnitModel.setUnitNumber(devUnitBean.getUnitNumber());
        devUnitModel.setUnitMaterial(devUnitBean.getUnitMaterial());
        devUnitModel.setRemark(devUnitBean.getRemark());

        devUnitModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, devUnitService.updateInfo(devUnitModel));
    }

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devUnitService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

}
