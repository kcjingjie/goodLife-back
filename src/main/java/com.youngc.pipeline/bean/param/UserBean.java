package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

/**
 * @author liweiqiang
 */
@Data
public class UserBean {

    private Long userId;

    private String userName;

    private String password;

    private String realName;

    private String userAddress;

    private String userBirth;

    private String userEmail;

    private Long userSex;

    private String userPhone;

    private Long status;

}
