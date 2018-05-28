package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.UserManagerModel;

public interface UserManagerService {
    UserManagerModel getUserDetails(Long userId);

    UserManagerModel updateUserDetails(UserManagerModel userManagerModel);

    boolean deleteUser(Long userId);

    boolean deleteUserList(String userIds);

    UserManagerModel addUser(UserManagerModel userManagerModel);

    Page getList(String keyword, int pageNum, int pageSize);

    void updatePassword(Long userId, String password, Long lastPerson);
}
