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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public Result postInfo(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setUnitId(pipeInfoBean.getUnitId());
        pipeInfoModel.setDeviceAlias(pipeInfoBean.getDeviceAlias());
        pipeInfoModel.setDeviceName(pipeInfoBean.getDeviceName());
        pipeInfoModel.setStatus(pipeInfoBean.getStatus());
        pipeInfoModel.setPressurePipe(pipeInfoBean.getPressurePipe());
        pipeInfoModel.setDeviceDesc(pipeInfoBean.getDeviceDesc());
        pipeInfoModel.setDeviceType(pipeInfoBean.getDeviceType());
        pipeInfoModel.setDeviceEquip(pipeInfoBean.getDeviceEquip());
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
    public Result putInfo(@RequestBody PipeInfoBean pipeInfoBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        PipeInfoModel pipeInfoModel = new PipeInfoModel();

        pipeInfoModel.setDeviceId(pipeInfoBean.getDeviceId());
        pipeInfoModel.setDeviceAlias(pipeInfoBean.getDeviceAlias());
        pipeInfoModel.setDeviceName(pipeInfoBean.getDeviceName());
        pipeInfoModel.setPressurePipe(pipeInfoBean.getPressurePipe());
        pipeInfoModel.setStatus(pipeInfoBean.getStatus());
        pipeInfoModel.setDeviceEquip(pipeInfoBean.getDeviceEquip());
        pipeInfoModel.setDeviceDesc(pipeInfoBean.getDeviceDesc());
        pipeInfoModel.setDeviceType(pipeInfoBean.getDeviceType());
        pipeInfoModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.updateInfo(pipeInfoModel));
    }

    /**
     * 删除设备信息
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
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


    /**
     * 查询标准参数名字（去重）
     * @return
     */
    @GetMapping("/getParaName")
    public Result getParaName(){

        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getParaName());
    }

    /**
     * 根据单位id查询标准参数信息
     * @param unitId
     * @return
     */
    @GetMapping("/getParaValue")
    public Result getParaName(@RequestParam Long unitId){

        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getParaValue(unitId));
    }

    /**
     * 根据id查询文件
     * @param deviceId
     * @return
     */
    @GetMapping("/showFile/{deviceId}")
    public Result showFile(@PathVariable Long deviceId){
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.showFile(deviceId));
    }

    /**
     * 查询管道详情
     * @param devId
     * @return
     */
    @GetMapping("/getDeatail")
    public Result getDeatail(@RequestParam Long devId){
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.getDeatail(devId));
    }

    /**
     * 导出管道Excel
     * @param unitId
     * @return
     */
    @GetMapping("/excelDownload")
    public Result excelDownload(@RequestParam Long unitId,
                                HttpServletRequest request,HttpServletResponse response){
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.excelDownload(request,response,unitId));
    }


    /**
     * 导入Excel文件
     * @param unitId,file
     * @return
     */
    @PostMapping(value ="/upload")
    public Result uploadFileInfo(@RequestParam String unitId,@RequestParam MultipartFile file) {
        return ResultGenerator.generate(ResultCode.SUCCESS,infoManagerService.readExcel(Long.valueOf(unitId),file));
    }
}
