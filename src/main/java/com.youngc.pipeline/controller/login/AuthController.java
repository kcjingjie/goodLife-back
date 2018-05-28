package com.youngc.pipeline.controller.login;

import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.login.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liweiqiang
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    Result login(String userName, String password) {

        if (userName == null || userName.equals("")) {

            return ResultGenerator.generate(ResultCode.FAIL);
        }

        if (password == null || password.equals("")) {
            return ResultGenerator.generate(ResultCode.FAIL);
        }

        return ResultGenerator.generate(ResultCode.SUCCESS, authService.login(userName, password));
    }

//    @PostMapping("/updatePassword")
//    @ResponseBody
//    Result updatePassword(String token, String oldPassword, String newPassword) {
//
//        if (authService.updatePassword(token, oldPassword, newPassword)) {
//            return ResultGenerator.generate(ResultCode.SUCCESS);
//        }
//        return ResultGenerator.generate(ResultCode.FAIL);
//    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    @ResponseBody
    Result logout(String token) {


        if (authService.logout(token)) {
            return ResultGenerator.generate(ResultCode.SUCCESS);
        }

        return ResultGenerator.generate(ResultCode.FAIL);
    }
}
