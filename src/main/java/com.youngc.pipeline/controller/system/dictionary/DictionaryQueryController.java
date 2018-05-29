package com.youngc.pipeline.controller.system.dictionary;

import com.youngc.pipeline.bean.param.DictionaryQueryBean;
import com.youngc.pipeline.bean.param.UserBean;
import com.youngc.pipeline.model.DictionaryQueryModel;
import com.youngc.pipeline.model.UserManagerModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.dictionary.DictionaryQueryService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.Calendar;

@RestController
@RequestMapping("/dict")
public class DictionaryQueryController {

    @Autowired
    private DictionaryQueryService dictionaryQueryService;

    /**
     * 获取数据字典的数据
     * @param dictName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "/get")
    public Result getDictionaryList(@RequestParam String dictName,@RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(dictionaryQueryService.getList(dictName,pageNum,pageSize));
    }

    /**
     * 添加数据字典
     * @param dictionaryQueryBean
     * @return
     */
    @PostMapping
    public  Result PostDict(@RequestBody DictionaryQueryBean dictionaryQueryBean){
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DictionaryQueryModel dictionaryQueryModel = new DictionaryQueryModel();

        dictionaryQueryModel.setDictValue(dictionaryQueryBean.getDictValue());
        dictionaryQueryModel.setDictName(dictionaryQueryBean.getDictName());
        dictionaryQueryModel.setRemark(dictionaryQueryBean.getRemark());
        dictionaryQueryModel.setStatus(dictionaryQueryBean.getStatus());
        dictionaryQueryModel.setAddPerson(user.getUserId());
        dictionaryQueryModel.setAddTime(Calendar.getInstance().getTime());
        dictionaryQueryModel.setLastPerson(user.getUserId());
        dictionaryQueryModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS,
                dictionaryQueryService.addDict(dictionaryQueryModel));
    }

    /**
     * 批量删除数据字典
     * @param idList
     * @return
     */
    @DeleteMapping
    public Result deleteDictList(@RequestParam("idList") String idList) {

        dictionaryQueryService.deleteDictList(idList);

        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 根据id查询数据字典的数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getUserDetails(@PathVariable Long id) {

        return ResultGenerator.generate(ResultCode.SUCCESS, dictionaryQueryService.getDictInfo(id));
    }

    /**
     * 修改数据字典的数据
     * @param dictionaryQueryBean
     * @return
     */
    @PutMapping
    public Result putUser(@RequestBody DictionaryQueryBean dictionaryQueryBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        DictionaryQueryModel dictionaryQueryModel = new DictionaryQueryModel();

        dictionaryQueryModel.setDictValue(dictionaryQueryBean.getDictValue());
        dictionaryQueryModel.setDictName(dictionaryQueryBean.getDictName());
        dictionaryQueryModel.setRemark(dictionaryQueryBean.getRemark());
        dictionaryQueryModel.setStatus(dictionaryQueryBean.getStatus());
        dictionaryQueryModel.setAddPerson(user.getUserId());
        dictionaryQueryModel.setAddTime(Calendar.getInstance().getTime());
        dictionaryQueryModel.setLastPerson(user.getUserId());
        dictionaryQueryModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS, dictionaryQueryService.updateDictInfo(dictionaryQueryModel));
    }

}
