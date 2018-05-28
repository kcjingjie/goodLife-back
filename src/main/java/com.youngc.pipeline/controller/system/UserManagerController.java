package com.youngc.pipeline.controller.system;

import com.youngc.pipeline.bean.param.UserBean;
import com.youngc.pipeline.model.UserManagerModel;
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
        UserManagerModel usersManagerModel = new UserManagerModel();

        usersManagerModel.setId(userBean.getId());
        usersManagerModel.setUserName(userBean.getUserName());
        usersManagerModel.setRealName(userBean.getRealName());
        usersManagerModel.setUserPhone(userBean.getUserPhone());
        usersManagerModel.setUserAddress(userBean.getUserAddress());
        usersManagerModel.setUserEmail(userBean.getUserEmail());
        usersManagerModel.setUserSex(userBean.getUserSex());

        usersManagerModel.setLastPerson(user.getUserId());
        usersManagerModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS, userManagerService.updateUserDetails(usersManagerModel));
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
        UserManagerModel usersManagerModel = new UserManagerModel();

        usersManagerModel.setUserName(userBean.getUserName());
        usersManagerModel.setRealName(userBean.getRealName());
        usersManagerModel.setPassword(userBean.getPassword());
        usersManagerModel.setUserAddress(userBean.getUserAddress());
        usersManagerModel.setUserBirth(userBean.getUserBirth());
        usersManagerModel.setUserEmail(userBean.getUserEmail());
        usersManagerModel.setUserSex(userBean.getUserSex());
        usersManagerModel.setUserPhone(userBean.getUserPhone());
        usersManagerModel.setAddPerson(user.getUserId());
        usersManagerModel.setAddTime(Calendar.getInstance().getTime());
        usersManagerModel.setLastPerson(user.getUserId());
        usersManagerModel.setLastTime(Calendar.getInstance().getTime());

        return ResultGenerator.generate(ResultCode.SUCCESS,
                userManagerService.addUser(usersManagerModel));
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId) {

        userManagerService.deleteUser(userId);

        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 批量删除用户
     * @param deleteIds
     * @return
     */
    @DeleteMapping
    public Result deleteUserList(@RequestParam("deleteIds") String deleteIds) {

        userManagerService.deleteUserList(deleteIds);

        return ResultGenerator.generate(ResultCode.SUCCESS);
    }

    /**
     * 修改密码
     */
    @PostMapping("/{userId}/password")
    public Result updatePassword(@PathVariable Long userId, @RequestParam String password) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");

        userManagerService.updatePassword(userId, password, user.getUserId());
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
}
