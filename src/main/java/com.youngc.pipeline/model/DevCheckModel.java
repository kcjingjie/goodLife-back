package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevCheckModel {
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

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
