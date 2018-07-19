package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.DevDataReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataReport")
public class DevDataReportController {
    @Autowired
    private DevDataReportService devDataReportService;

    /**
     * 根据模型划分查询设备个数
     * @return
     */
    @GetMapping("/byModel")
    public Result getDevCountByModel(){
        return ResultGenerator.generate(ResultCode.SUCCESS,devDataReportService.getDevCountByModel());
    }

    /**
     * 根据管道的类型查询设备个数
     */
    @GetMapping("/byType")
    public Result getDevCountByType(){
        return ResultGenerator.generate(ResultCode.SUCCESS,devDataReportService.getDevConutByType());
    }

    /**
     * 根据单位查询
     */
    @GetMapping("/byUnit")
    public Result getDevCountByUnit(){
        return ResultGenerator.generate(ResultCode.SUCCESS,devDataReportService.getDevCountByUnit());
    }

    /**
     * 查询检修率
     * @return
     */
    @GetMapping("/getLeakRatio")
    public Result getLeakRatio(){
        return ResultGenerator.generate(ResultCode.SUCCESS,devDataReportService.getLeakRatio());
    }

}
