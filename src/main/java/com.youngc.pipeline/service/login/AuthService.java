package com.youngc.pipeline.service.login;

import java.util.Map;

/**
 * @author liweiqiang
 */
public interface AuthService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Map login(String username, String password);

    /**
     * 更新密码
     * @param token
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(String token, String oldPassword, String newPassword);

    /**
     * 登出
     * @param token
     * @return
     */
    boolean logout(String token);

    /**
     * token验证
     * @param token
     * @return
     */
    boolean isTokenExist(String token);

    /**
     * 根据用户ID获取token
     * @param token
     * @return
     */
    Long getUserIdByToken(String token);

}
