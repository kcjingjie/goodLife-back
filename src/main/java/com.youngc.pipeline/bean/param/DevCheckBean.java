package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevCheckBean {
    private Long id;

    private Long devId;

    private String planName;

    private String deviceName;

    private String exeTime;

    private Long exeCycle;

    private String checkOrganize;

    private String checkUser;

    private String exeUser;

    private String exeDesc;

    private String remark;
}
