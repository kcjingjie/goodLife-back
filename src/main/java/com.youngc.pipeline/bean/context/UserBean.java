package com.youngc.pipeline.bean.context;

import lombok.Data;

@Data
public class UserBean {
    private long userId;
    private String username;
    private String userRole;
    private String token;
}
