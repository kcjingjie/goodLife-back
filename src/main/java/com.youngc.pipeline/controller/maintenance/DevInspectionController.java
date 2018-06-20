package com.youngc.pipeline.controller.maintenance;

import com.youngc.pipeline.bean.param.DevInspectionBean;
import com.youngc.pipeline.bean.param.PipeInfoBean;
import com.youngc.pipeline.model.DevInspectionModel;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.maintenance.DevInspectionService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inspection")
public class DevInspectionController {
    @Autowired
    private DevInspectionService devInspectionService;

    //模糊查询巡检计划信息
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam String keyWord, @RequestParam String devName, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devInspectionService.getList(keyWord,devName,pageNum,pageSize));
    }

    //添加巡检计划
    @PostMapping
    public Result post(@RequestBody DevInspectionBean devInspectionBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevInspectionModel devInspectionModel = new DevInspectionModel();

        devInspectionModel.setDevId(devInspectionBean.getDevId());
        devInspectionModel.setPlanName(devInspectionBean.getPlanName());
        devInspectionModel.setExeTime(devInspectionBean.getExeTime());
        devInspectionModel.setExeCycle(devInspectionBean.getExeCycle());
        devInspectionModel.setExeUser(devInspectionBean.getExeUser());
        devInspectionModel.setExeDesc(devInspectionBean.getExeDesc());
        devInspectionModel.setRemark(devInspectionBean.getRemark());
        devInspectionModel.setAddPerson(user.getUserId());
        devInspectionModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devInspectionService.insert(devInspectionModel));
    }

    //删除巡检计划
    @DeleteMapping(value = "/del")
    public Result delete(@RequestParam("idList") String idList) {
        devInspectionService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    //根据id查询巡检计划信息
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devInspectionService.getInfo(id));
    }

    //修改巡检计划信息
    @PutMapping
    public Result put(@RequestBody DevInspectionBean devInspectionBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevInspectionModel devInspectionModel = new DevInspectionModel();

        devInspectionModel.setId(devInspectionBean.getId());
        devInspectionModel.setDevId(devInspectionBean.getDevId());
        devInspectionModel.setPlanName(devInspectionBean.getPlanName());
        devInspectionModel.setExeTime(devInspectionBean.getExeTime());
        devInspectionModel.setExeCycle(devInspectionBean.getExeCycle());
        devInspectionModel.setExeUser(devInspectionBean.getExeUser());
        devInspectionModel.setExeDesc(devInspectionBean.getExeDesc());
        devInspectionModel.setRemark(devInspectionBean.getRemark());
        devInspectionModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devInspectionService.updateInfo(devInspectionModel));
    }

    //查询设备列表
    @GetMapping("/getDev")
    public Result getModel(){
        return ResultGenerator.generate(ResultCode.SUCCESS,devInspectionService.getDevList());
    }
}
