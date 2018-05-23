package com.youngc.pipeline.service.user;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.UserUserManagerModel;

public interface UserManagerService {
    UserUserManagerModel getUserDetails(Long userId);

    UserUserManagerModel updateUserDetails(UserUserManagerModel userUserManagerModel);

    boolean deleteUser(Long userId);

    boolean deleteUserList(String userIds);

    UserUserManagerModel addUser(UserUserManagerModel userUserManagerModel);

    Page getList(String keyword, int pageNum, int pageSize);

    void updatePassword(Long userId, String password, Long lastPerson);
}
