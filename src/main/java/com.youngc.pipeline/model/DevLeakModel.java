package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevLeakModel {

    private Long id;

    private Long devId;

    private String leakNo;

    private String deviceName;

    private String leakDegree;

    private String occurTime;

    private String planExeTime;

    private String handleMethod;

    private String remark;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
