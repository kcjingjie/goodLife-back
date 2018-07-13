package com.youngc.pipeline.controller.maintenance;

import com.youngc.pipeline.bean.param.DevLeakBean;
import com.youngc.pipeline.model.DevLeakModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.maintenance.DevLeakService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leakManager")
public class DevLeakController {
    @Autowired
    private DevLeakService devLeakService;

    /**
     * 分页检索泄漏点信息
     * @param keyWord
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam String keyWord, @RequestParam String devName, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devLeakService.getList(keyWord,devName,pageNum,pageSize));
    }

    /**
     * 添加泄漏点信息
     * @param devLeakBean
     * @return
     */
    @PostMapping
    public Result postInfo(@RequestBody DevLeakBean devLeakBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevLeakModel devLeakModel = new DevLeakModel();

        devLeakModel.setDevId(devLeakBean.getDevId());
        devLeakModel.setLeakNo(devLeakBean.getLeakNo());
        devLeakModel.setLeakDegree(devLeakBean.getLeakDegree());
        devLeakModel.setOccurTime(devLeakBean.getOccurTime());
        devLeakModel.setPlanExeTime(devLeakBean.getPlanExeTime());
        devLeakModel.setHandleMethod(devLeakBean.getHandleMethod());
        devLeakModel.setRemark(devLeakBean.getRemark());
        devLeakModel.setAddPerson(user.getUserId());
        devLeakModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devLeakService.insert(devLeakModel));
    }

    /**
     * 删除泄漏点信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devLeakService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 根据id查询泄漏点信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devLeakService.getInfo(id));
    }

    /**
     * 修改泄漏点信息
     * @param devLeakBean
     * @return
     */
    @PutMapping
    public Result putInfo(@RequestBody DevLeakBean devLeakBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevLeakModel devLeakModel = new DevLeakModel();

        devLeakModel.setId(devLeakBean.getId());
        devLeakModel.setDevId(devLeakBean.getDevId());
        devLeakModel.setLeakNo(devLeakBean.getLeakNo());
        devLeakModel.setLeakDegree(devLeakBean.getLeakDegree());
        devLeakModel.setOccurTime(devLeakBean.getOccurTime());
        devLeakModel.setPlanExeTime(devLeakBean.getPlanExeTime());
        devLeakModel.setHandleMethod(devLeakBean.getHandleMethod());
        devLeakModel.setRemark(devLeakBean.getRemark());
        devLeakModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devLeakService.updateInfo(devLeakModel));
    }

    /**
     * 查询所有设备
     * @return
     */
    @GetMapping("/getDev")
    public Result getModel(){
        return ResultGenerator.generate(ResultCode.SUCCESS, devLeakService.getDevList());
    }

    /**
     * 查询编号是否唯一
     * @param leakNo
     * @return
     */
    @GetMapping("/getNo")
    public Result getInfoByNo(@RequestParam String leakNo) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devLeakService.getInfoByNo(leakNo));
    }
}
