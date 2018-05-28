package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.bean.param.UserBean;
import com.youngc.pipeline.model.UserUserManagerModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.system.UserManagerService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

/**
 * @author liweiqiang
 */
@RestController
@RequestMapping("/user")
public class UserManagerController {
    @Autowired
    private UserManagerService userManagerService;

    /**
     * 查询用户信息
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    public Result getUserList(@RequestParam String keyword, @RequestParam int pageNum, @RequestParam int pageSize) {
        return ResultGenerator.generate(userManagerService.getList(keyword, pageNum, pageSize));
    }
    @GetMapping("/{userId}")
    public Result getUserDetails(@PathVariable Long userId) {

        return ResultGenerator.generate(ResultCode.SUCCESS, userManagerService.getUserDetails(userId));
    }

    /**
     * 修改用户信息
     * @param userBean
     * @return
     */
    @PutMapping
    public Result putUser(@RequestBody UserBean userBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        System.out.println((com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user"));
        UserUserManagerModel userUserManagerModel = new UserUserManagerModel();

        userUserManagerModel.setId(userBean.getId());
        userUserManagerModel.setUserName(userBean.getUserName());
        userUserManagerModel.setRealName(userBean.getRealName());
        userUserManagerModel.setUserPhone(userBean.getUserPhone());
        userUserManagerModel.setUserAddress(userBean.getUserAddress());
        userUserManagerModel.setUserEmail(userBean.getUserEmail());
        userUserManagerModel.setUserSex(userBean.getUserSex());

        userUserManagerModel.setLastPerson(user.getUserId());
        userUserManagerModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS, userManagerService.updateUserDetails(userUserManagerModel));
    }

    /**
     * 添加用户
     * @param userBean
     * @return
     */
    @PostMapping
    public Result postUser(@RequestBody UserBean userBean) {
         com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");

        UserUserManagerModel userUserManagerModel = new UserUserManagerModel();

        userUserManagerModel.setUserName(userBean.getUserName());
        userUserManagerModel.setRealName(userBean.getRealName());
        userUserManagerModel.setPassword(userBean.getPassword());

        userUserManagerModel.setAddPerson(user.getUserId());
        userUserManagerModel.setAddTime(Calendar.getInstance().getTime());
        userUserManagerModel.setLastPerson(user.getUserId());
        userUserManagerModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS,
                userManagerService.addUser(userUserManagerModel));
    }
}
