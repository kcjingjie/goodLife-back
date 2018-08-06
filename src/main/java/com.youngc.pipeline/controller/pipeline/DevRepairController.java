package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevRepairBean;
import com.youngc.pipeline.model.DevRepairModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevRepairService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/devRepair")
public class DevRepairController {

    @Autowired
    DevRepairService devRepairService;
    /**
     * 分页获取设备备件
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long deviceId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(ResultCode.SUCCESS,devRepairService.getList(deviceId,pageNum,pageSize));
    }

    /**
     * 根据id查询设备备件
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devRepairService.getInfo(id));
    }

    /**
     * 修改设备备件
     * @param devRepairBean
     * @return
     */
    @PutMapping
    public Result putInfo(@RequestBody DevRepairBean devRepairBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevRepairModel devRepairModel = new DevRepairModel();

        devRepairModel.setId(devRepairBean.getId());
        devRepairModel.setBrand(devRepairBean.getBrand());
        devRepairModel.setCompany(devRepairBean.getCompany());
        devRepairModel.setCycle(devRepairBean.getCycle());
        devRepairModel.setManufactor(devRepairBean.getManufactor());
        devRepairModel.setMaterial(devRepairBean.getMaterial());
        devRepairModel.setModel(devRepairBean.getModel());
        devRepairModel.setPrice(devRepairBean.getPrice());
        devRepairModel.setStock(devRepairBean.getStock());
        devRepairModel.setSpecification(devRepairBean.getSpecification());
        devRepairModel.setQuantity(devRepairBean.getQuantity());
        devRepairModel.setCycle(devRepairBean.getCycle());
        devRepairModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devRepairService.updateInfo(devRepairModel));
    }

    /**
     * 添加设备标准参数信息
     * @param devRepairBean
     * @return
     */
    @PostMapping
    public Result postInfo(@RequestBody DevRepairBean devRepairBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevRepairModel devRepairModel = new DevRepairModel();

        devRepairModel.setId(devRepairBean.getId());
        devRepairModel.setDeviceId(devRepairBean.getDeviceId());
        devRepairModel.setBrand(devRepairBean.getBrand());
        devRepairModel.setCompany(devRepairBean.getCompany());
        devRepairModel.setCycle(devRepairBean.getCycle());
        devRepairModel.setManufactor(devRepairBean.getManufactor());
        devRepairModel.setMaterial(devRepairBean.getMaterial());
        devRepairModel.setModel(devRepairBean.getModel());
        devRepairModel.setPrice(devRepairBean.getPrice());
        devRepairModel.setStock(devRepairBean.getStock());
        devRepairModel.setSpecification(devRepairBean.getSpecification());
        devRepairModel.setQuantity(devRepairBean.getQuantity());
        devRepairModel.setCycle(devRepairBean.getCycle());
        devRepairModel.setLastPerson(user.getUserId());
        devRepairModel.setAddPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devRepairService.insert(devRepairModel));
    }

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devRepairService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 导入Excel文件
     * @param devId,file
     * @return
     */
    @PostMapping(value ="/upload")
    public Result uploadFileInfo(@RequestParam String devId,@RequestParam MultipartFile file) {
        return ResultGenerator.generate(ResultCode.SUCCESS,devRepairService.readExcel(Long.valueOf(devId),file));
    }

    /**
     * 导出Excel文件
     * @param request
     * @param response
     * @param devId
     * @return
     */
    @GetMapping("/download")
    public String downloadFileInfo(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String devId) {
        return devRepairService.downloadFileInfo(request, response,Long.parseLong(devId));
    }
}
