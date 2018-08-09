package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipeInfoModel {
    private Long id;

    private Long deviceId;

    private Long unitId;

    //private Long modelId;

    private Long imageId;

    //private String modelName;

    private String deviceAlias;

    private Long deviceType;

    private Long deviceEquip;

    private  String deviceEquipName;

    private String deviceTypeName;

    private String deviceName;

   // private String deviceCode;

    private String customCode;

    private String deviceDesc;

    private int pressurePipe;

    private int status;

    private String paraName;

    private String paraId;

    private String paraValue;

    private String paraUnit;

    //private String address;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;

}
