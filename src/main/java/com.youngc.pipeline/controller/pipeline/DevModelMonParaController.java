package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.DevModelMonParaBean;
import com.youngc.pipeline.model.DevModelMonParaModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevModelMonParaService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devModelMonPara")
public class DevModelMonParaController {

    @Autowired
    private DevModelMonParaService devModelMonParaService;

    /**
     * 分页获取模型监测参数信息
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getList(@RequestParam Long modelId, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(devModelMonParaService.getList(modelId,pageNum,pageSize));
    }

    /**
     * 添加模型监测参数信息
     * @param devModelMonParaBean
     * @return
     */
    @PostMapping
    public Result postInfo(@RequestBody DevModelMonParaBean devModelMonParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevModelMonParaModel devModelMonParaModel = new DevModelMonParaModel();

        devModelMonParaModel.setModelId(devModelMonParaBean.getModelId());
        devModelMonParaModel.setParaName(devModelMonParaBean.getParaName());
        devModelMonParaModel.setParaId(devModelMonParaBean.getParaId());
        devModelMonParaModel.setParaDataType(devModelMonParaBean.getParaDataType());
        devModelMonParaModel.setParaType(devModelMonParaBean.getParaType());
        devModelMonParaModel.setParaUnit(devModelMonParaBean.getParaUnit());
        devModelMonParaModel.setRemark(devModelMonParaBean.getRemark());
        devModelMonParaModel.setAddPerson(user.getUserId());
        devModelMonParaModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, devModelMonParaService.insert(devModelMonParaModel));
    }

    /**
     * 根据id获取模型监测参数信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDetails(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, devModelMonParaService.getInfo(id));
    }

    /**
     * 修改模型监测参数信息
     * @param devModelMonParaBean
     * @return
             */
    @PutMapping
    public Result putInfo(@RequestBody DevModelMonParaBean devModelMonParaBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DevModelMonParaModel devModelMonParaModel = new DevModelMonParaModel();

        devModelMonParaModel.setId(devModelMonParaBean.getId());
        devModelMonParaModel.setModelId(devModelMonParaBean.getModelId());
        devModelMonParaModel.setParaName(devModelMonParaBean.getParaName());
        devModelMonParaModel.setParaId(devModelMonParaBean.getParaId());
        devModelMonParaModel.setParaDataType(devModelMonParaBean.getParaDataType());
        devModelMonParaModel.setParaType(devModelMonParaBean.getParaType());
        devModelMonParaModel.setParaUnit(devModelMonParaBean.getParaUnit());
        devModelMonParaModel.setRemark(devModelMonParaBean.getRemark());

        devModelMonParaModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, devModelMonParaService.updateInfo(devModelMonParaModel));
    }

    /**
     * 删除模型监测参数信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/del")
    public Result deleteInfo(@RequestParam("idList") String idList) {
        devModelMonParaService.delete(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
    /**
     * 查询模型监测参数的标识是否唯一
     */
    @GetMapping("/code")
    public Result getInfoByCode(@RequestParam Long modelId,@RequestParam String paraId) {
        return ResultGenerator.generate(ResultCode.SUCCESS,devModelMonParaService.getInfoByCode(modelId,paraId));
    }
}
