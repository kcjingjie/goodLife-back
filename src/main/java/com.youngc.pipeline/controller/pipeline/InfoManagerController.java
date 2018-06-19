package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.InfoManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pipeInfo")
public class InfoManagerController {

    @Autowired
    private InfoManagerService infoManagerService;

    @GetMapping("/orgUnitTree")
    public Result getOrgUnitTree() {

        return ResultGenerator.generate(ResultCode.SUCCESS, infoManagerService.getOrgUnitTree());
    }

    @GetMapping(value = "/getList")
    public Result getDictionaryList(@RequestParam String keyWord,@RequestParam Long pid, @RequestParam int pageNum, @RequestParam int pageSize){
        return ResultGenerator.generate(infoManagerService.getList(keyWord,pid,pageNum,pageSize));
    }
}
