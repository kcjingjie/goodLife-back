package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevCheckModel {
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

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
