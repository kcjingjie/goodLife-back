package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.UserManagerMapper;
import com.youngc.pipeline.model.UserManagerModel;
import com.youngc.pipeline.service.system.UserManagerService;
import com.youngc.pipeline.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liweiqiang
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerMapper userManagerMapper;

    public Page getList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) userManagerMapper.getList(keyword);
    }

    public UserManagerModel getUserDetails(Long userId) {

        return userManagerMapper.getUserInfo(userId);
    }

    public UserManagerModel updateUserDetails(UserManagerModel userManagerModel) {

        userManagerMapper.updateUserInfo(userManagerModel);

        return userManagerModel;
    }

    public void updatePassword(Long userId, String password, Long lastPerson) {
        String hashPwd = BCryptUtil.hashpw(password, BCryptUtil.gensalt(12));
        userManagerMapper.updatePassword(userId, hashPwd, lastPerson);
    }

    public boolean deleteUser(Long userId) {

        userManagerMapper.deleteUser(userId);

        return true;
    }

    public boolean deleteUserList(String userIds) {

        userManagerMapper.deleteUserList(userIds);

        return true;
    }

    public UserManagerModel addUser(UserManagerModel userManagerModel) {

        userManagerModel.setPassword(BCryptUtil.hashpw(userManagerModel
                .getPassword(), BCryptUtil.gensalt(12)));

        userManagerMapper.insertNewUser(userManagerModel);
        return userManagerModel;
    }
}
