package com.youngc.pipeline.controller.pipeline;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.HistoryRecordService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  liuyan
 * @desc 历史记录
 * @time 2018年8月7日17:19:42
 */
@RestController
@RequestMapping("/historyRecord")
public class HistoryRecordController {

    @Autowired
    private HistoryRecordService historyRecordService;

    @ApiOperation("获得历史记录")
    @GetMapping("/getList" )
    public Result getHistoryRecord(@RequestParam String keyWord){
        System.out.println("获得历史记录");
        return ResultGenerator.generate(ResultCode.SUCCESS,historyRecordService.getRecordList());
    }


}
