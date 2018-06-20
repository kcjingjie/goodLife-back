package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class DevInspectionBean {
    private Long id;

    private Long devId;

    private String planName;

    private String deviceName;

    private Date exeTime;

    private Long exeCycle;

    private Long exeUser;

    private String exeDesc;

    private String remark;
}
