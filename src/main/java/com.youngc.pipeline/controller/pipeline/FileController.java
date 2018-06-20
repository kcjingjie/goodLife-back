package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;

    @ApiOperation("获取文件信息")
    @GetMapping
    public Result getFileInfo(@RequestParam String orgId, String unitId) {
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.getFileInfo(orgId, unitId));
    }

}
