package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipeInfoModel {
    private Long id;

    private Long deviceId;

    private Long unitId;

    private Long modelId;

    private String modelName;

    private String deviceAlias;

    private String deviceName;

    private String deviceCode;

    private String customCode;

    private String deviceDesc;

    private int status;

    private String address;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;

}
