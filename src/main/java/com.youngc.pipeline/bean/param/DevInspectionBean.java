package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class DevInspectionBean {
    private Long id;

    private Long devId;

    private String planName;

    private String deviceName;

    private String exeTime;

    private Long exeCycle;

    private String exeUser;

    private String exeDesc;

    private String remark;
}
