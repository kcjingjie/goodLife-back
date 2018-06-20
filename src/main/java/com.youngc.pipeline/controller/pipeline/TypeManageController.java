package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.SysRoleBean;
import com.youngc.pipeline.bean.param.TypeManageBean;
import com.youngc.pipeline.model.SysRoleModel;
import com.youngc.pipeline.model.TypeManageModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.TypeManageService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
public class TypeManageController {

    @Autowired
    TypeManageService typeManageService;

    /**
     * 查询类型
     * @param keyWord
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/getList")
    public Result getDictionaryList(@RequestParam String keyWord, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(typeManageService.getList(keyWord,pageNum,pageSize));
    }

    /**
     * 通过编码查询类型
     * @param value
     * @return
     */
    @GetMapping(value = "/getByValue")
    public Result getIdByCode(@RequestParam String value){
        return ResultGenerator.generate(ResultCode.SUCCESS,typeManageService.getByCode(value));
    }

    /**
     * 通过id查询类型
     * @param id
     * @return
     */
    @GetMapping(value = "/getInfo/{id}")
    public Result getTypeInfo(@PathVariable Long id) {
        return ResultGenerator.generate(ResultCode.SUCCESS, typeManageService.getTypeInfo(id));
    }

    /**
     * 修改类型信息
     * @param typeManageBean
     * @return
     */
    @PutMapping(value = "/put")
    public Result putTypeInfo(@RequestBody TypeManageBean typeManageBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        TypeManageModel typeManageModel=new TypeManageModel();
        typeManageModel.setModelId(typeManageBean.getModelId());
        typeManageModel.setModelCode(typeManageBean.getModelCode());
        typeManageModel.setModelName(typeManageBean.getModelName());
        typeManageModel.setModelType(typeManageBean.getModelType());
        typeManageModel.setModelEquip(typeManageBean.getModelEquip());
        typeManageModel.setModelDesc(typeManageBean.getModelDesc());
        typeManageModel.setStatus(typeManageBean.getStatus());
        typeManageModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS, typeManageService.updateTypeInfo(typeManageModel));
    }

    /**
     * 添加类型信息
     * @param typeManageBean
     * @return
     */
    @PostMapping(value = "/post")
    public Result postRoleInfo(@RequestBody TypeManageBean typeManageBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        TypeManageModel typeManageModel=new TypeManageModel();
        typeManageModel.setModelCode(typeManageBean.getModelCode());
        typeManageModel.setModelName(typeManageBean.getModelName());
        typeManageModel.setModelType(typeManageBean.getModelType());
        typeManageModel.setModelEquip(typeManageBean.getModelEquip());
        typeManageModel.setModelDesc(typeManageBean.getModelDesc());
        typeManageModel.setAddPerson(user.getUserId());
        typeManageModel.setStatus(typeManageBean.getStatus());
        typeManageModel.setLastPerson(user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS,
                typeManageService.addType(typeManageModel));
    }

    /**
     * 删除类型信息
     * @param idList
     * @return
     */
    @DeleteMapping(value = "/deleteType")
    public Result deleteRoleList(@RequestParam("idList") String idList) {
        typeManageService.deleteTypeList(idList);
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
}
