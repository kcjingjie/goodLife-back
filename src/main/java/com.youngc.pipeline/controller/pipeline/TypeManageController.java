package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.TypeManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(value = "/get")
    public Result getDictionaryList(@RequestParam String keyWord, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(typeManageService.getList(keyWord,pageNum,pageSize));
    }
}
