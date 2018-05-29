package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.ModuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liweiqiang
 */
@RestController
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @ApiOperation("获取模块树")
    @GetMapping("/tree")
    public Result getTree(@RequestParam String keyword) {

        return ResultGenerator.generate(ResultCode.SUCCESS,moduleService.getTree(keyword));
    }
}
