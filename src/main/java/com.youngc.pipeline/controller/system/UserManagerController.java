package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liweiqiang
 */
@RestController
@RequestMapping("/user")
public class UserManagerController {
    @Autowired
    private UserManagerService userManagerService;

    @GetMapping
    public Result getUserList(@RequestParam String keyword, @RequestParam int pageNum, @RequestParam int pageSize) {
        return ResultGenerator.generate(userManagerService.getList(keyword, pageNum, pageSize));
    }
    @GetMapping("/{userId}")
    public Result getUserDetails(@PathVariable Long userId) {

        return ResultGenerator.generate(ResultCode.SUCCESS, userManagerService.getUserDetails(userId));
    }
}
