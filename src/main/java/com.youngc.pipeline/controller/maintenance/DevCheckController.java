package com.youngc.pipeline.controller.maintenance;

import com.youngc.pipeline.bean.param.DevCheckBean;
import com.youngc.pipeline.model.DevCheckModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.maintenance.DevCheckService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/check")
public class DevCheckController {

    @Autowired
    private DevCheckService devCheckService;

    /**
     * 模糊查询检验计划信息
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList( @RequestParam String devName, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devCheckService.getList(devName,pageNum,pageSize));
    }

    /**
     * 模糊查询检验计划信息
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getNeedList")
    public Result getNeedList( @RequestParam String devName, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devCheckService.getNeedList(devName,pageNum,pageSize));
    }

    /**
     * 添加检验计划
     */

    @PostMapping
    public Result postInfo(@RequestBody DevCheckBean devCheckBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevCheckModel devCheckModel = new DevCheckModel();

        devCheckModel.setDevId(devCheckBean.getDevId());
        devCheckModel.setPlanExeTime(devCheckBean.getPlanExeTime());
        devCheckModel.setCheckOrganize(devCheckBean.getCheckOrganize());
        devCheckModel.setExeCycle(devCheckBean.getExeCycle());
        devCheckModel.setRemark(devCheckBean.getRemark());
        devCheckModel.setAddPerson(user.getUserId());
        devCheckModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devCheckService.insert(devCheckModel));
    }

    /**
     * 删除检验计划
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devCheckService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 根据id查询检验计划信息
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devCheckService.getInfo(id));
    }

    /**
     * 修改检验计划信息
     */
    @PutMapping
    public Result putInfo(@RequestBody DevCheckBean devCheckBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevCheckModel devCheckModel = new DevCheckModel();

        devCheckModel.setId(devCheckBean.getId());
        devCheckModel.setDevId(devCheckBean.getDevId());
        devCheckModel.setPlanExeTime(devCheckBean.getPlanExeTime());
        devCheckModel.setCheckOrganize(devCheckBean.getCheckOrganize());
        devCheckModel.setExeCycle(devCheckBean.getExeCycle());
        devCheckModel.setRemark(devCheckBean.getRemark());
        devCheckModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devCheckService.updateInfo(devCheckModel));
    }

    /**
     *
     * @param devCheckBean
     * @return
     */
    @PutMapping("/submitInfo")
    public Result submitInfo(@RequestBody DevCheckBean devCheckBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevCheckModel devCheckModel = new DevCheckModel();

        devCheckModel.setId(devCheckBean.getId());
        devCheckModel.setCheckReport(devCheckBean.getCheckReport());
        devCheckModel.setPlanExeTime(devCheckBean.getPlanExeTime());
        devCheckModel.setLastExeTime(devCheckBean.getLastExeTime());
        devCheckModel.setCheckResult(devCheckBean.getCheckResult());
        devCheckModel.setDelayReason(devCheckBean.getDelayReason());
        devCheckModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devCheckService.submitInfo(devCheckModel));
    }
}
