package com.youngc.pipeline.service.user.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.UserUserManagerMapper;
import com.youngc.pipeline.model.UserUserManagerModel;
import com.youngc.pipeline.service.user.UserManagerService;
import com.youngc.pipeline.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserUserManagerMapper userUserManagerMapper;

    public Page getList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) userUserManagerMapper.getList(keyword);
    }

    public UserUserManagerModel getUserDetails(Long userId) {

        return userUserManagerMapper.getUserInfo(userId);
    }

    public UserUserManagerModel updateUserDetails(UserUserManagerModel userUserManagerModel) {

        userUserManagerMapper.updateUserInfo(userUserManagerModel);

        return userUserManagerModel;
    }

    public void updatePassword(Long userId, String password, Long lastPerson) {
        String hashPwd = BCryptUtil.hashpw(password, BCryptUtil.gensalt(12));
        userUserManagerMapper.updatePassword(userId, hashPwd, lastPerson);
    }

    public boolean deleteUser(Long userId) {

        userUserManagerMapper.deleteUser(userId);

        return true;
    }

    public boolean deleteUserList(String userIds) {

        userUserManagerMapper.deleteUserList(userIds);

        return true;
    }

    public UserUserManagerModel addUser(UserUserManagerModel userUserManagerModel) {

        userUserManagerModel.setPassword(BCryptUtil.hashpw(userUserManagerModel
                .getPassword(), BCryptUtil.gensalt(12)));

        userUserManagerMapper.insertNewUser(userUserManagerModel);
        return userUserManagerModel;
    }
}
