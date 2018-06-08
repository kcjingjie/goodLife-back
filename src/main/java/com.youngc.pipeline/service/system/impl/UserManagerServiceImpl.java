package com.youngc.pipeline.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.UserManagerMapper;
import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.model.UserManagerModel;
import com.youngc.pipeline.service.system.UserManagerService;
import com.youngc.pipeline.utils.BCryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author liweiqiang
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private UserManagerMapper userManagerMapper;

    /**
     * 批量获取用户信息
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) userManagerMapper.getList(keyword);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    public UserManagerModel getUserDetails(Long userId) {

        return userManagerMapper.getUserInfo(userId);
    }

    /**
     * 修改用户
     * @param userManagerModel
     * @return
     */
    public UserManagerModel updateUserDetails(UserManagerModel userManagerModel) {

        userManagerMapper.updateUserInfo(userManagerModel);

        return userManagerModel;
    }

    /**
     * 更新密码
     * @param userId
     * @param password
     * @param lastPerson
     */
    public void updatePassword(Long userId, String password, Long lastPerson) {
        String hashPwd = BCryptUtil.hashpw(password, BCryptUtil.gensalt(12));
        userManagerMapper.updatePassword(userId, hashPwd, lastPerson);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public boolean deleteUser(Long userId) {

        userManagerMapper.deleteUser(userId);

        return true;
    }

    /**
     * 批量删除用户
     * @param userIds
     * @return
     */
    public boolean deleteUserList(String userIds) {

        userManagerMapper.deleteUserList(userIds);

        return true;
    }

    /**
     * 添加用户
     * @param userManagerModel
     * @return
     */
    public UserManagerModel addUser(UserManagerModel userManagerModel,String roleIds,String droleIds,Long personId) {

        userManagerModel.setPassword(BCryptUtil.hashpw(userManagerModel
                .getPassword(), BCryptUtil.gensalt(12)));

       Long userId=userManagerMapper.insertNewUser(userManagerModel);
       putUserRole(roleIds,userId,personId);
       putUserDataRole(droleIds,userId,personId);
       return userManagerModel;
    }

    /**
     *获取单位列表
     */
    public List getUnitList() {
        return userManagerMapper.getUnitList();
    }

    public boolean putUserRole(String roleIds, Long userId, Long personId) {
        String[] IDS = roleIds.split(",");
        List<String> roleId = Arrays.asList(IDS);
        userManagerMapper.deleteUserRole(userId);

        userManagerMapper.insertUserRole(roleId, userId, personId);
        return true;
    }
    public boolean putUserDataRole(String droleIds, Long userId, Long personId) {
        String[] IDS = droleIds.split(",");
        List<String> droleId = Arrays.asList(IDS);
        userManagerMapper.deleteUserDataRole(userId);

        userManagerMapper.insertUserDataRole(droleId, userId, personId);
        return true;
    }

}
