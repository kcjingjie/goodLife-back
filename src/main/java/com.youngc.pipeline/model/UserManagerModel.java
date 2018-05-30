package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserManagerModel {

    private Long id;

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

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}