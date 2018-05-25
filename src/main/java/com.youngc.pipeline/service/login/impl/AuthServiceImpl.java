package com.youngc.pipeline.service.login.impl;

import com.youngc.pipeline.exception.ServiceException;
import com.youngc.pipeline.mapper.login.AuthTokenMapper;
import com.youngc.pipeline.mapper.login.AuthUserMapper;
import com.youngc.pipeline.model.UserUserManagerModel;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.service.login.AuthService;
import com.youngc.pipeline.utils.BCryptUtil;
import com.youngc.pipeline.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liweiqiang
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthUserMapper authUserMapper;

    @Autowired
    private AuthTokenMapper authTokenMapper;

    private Long zero = 0L;

    public Map login(String username, String rawPassword) throws ServiceException {

        UserUserManagerModel user = authUserMapper.getUserByUsername(username);

        // login successfully
        if (BCryptUtil.checkpw(rawPassword, user.getPassword())) {

            // 重新登录 或者 首次登录 都会生成新的 token
            String token = EncryptUtil.encodeMD5(user.getId() + Calendar.getInstance().getTime().toString());

            // user already logged in
            if (!zero.equals(authTokenMapper.isTokenExistsById(user.getId()))) {
                authTokenMapper.updateToken(user.getId(), token);
            } else {
                // 用户首次登陆
                authTokenMapper.insertNewToken(user.getId(), token);
            }
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("token", token);
            result.put("username", username);
            result.put("realname", user.getRealName());
            return result;
        }

        throw new ServiceException(ResultCode.USER_OR_PASS_ERROR, new Throwable(ResultCode.USER_OR_PASS_ERROR.getMsg()));
    }


    public boolean updatePassword(String token, String oldPassword, String newPassword) {

        Long userId = authTokenMapper.getUserIdByToken(token);

        String hashedPasswd = authUserMapper.getHashedPasswd(userId);

        if (!BCryptUtil.checkpw(oldPassword, hashedPasswd)) {
            return false;
        }

        if (BCryptUtil.checkpw(newPassword, hashedPasswd)) {
            return false;
        }

        if (authUserMapper.updateUserPassword(userId, BCryptUtil.hashpw(newPassword, BCryptUtil.gensalt(12))) != 1) {
            return false;
        }

        return true;
    }


    public boolean logout(String token) {
        if (zero.equals(authTokenMapper.isTokenExists(token))) {
            return false;
        }

        authTokenMapper.delete(token);
        return true;
    }

    public boolean isTokenExist(String token) {
        return !authTokenMapper.isTokenExists(token).equals(0);
    }

    public Long getUserIdByToken(String token) {
        return authTokenMapper.getUserIdByToken(token);
    }
}
