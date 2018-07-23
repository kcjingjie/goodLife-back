package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevCheckBean {
    private Long id;

    private Long devId;

    private String deviceName;

    private String planExeTime;

    private String lastExeTime;

    private Long deviceType;

    private Long deviceEquip;

    private String delayTime;

    private String delayReason;

    private Long exeCycle;

    private Long checkResult;

    private String checkOrganize;

    private String checkReport;

    private String remark;
}
