package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevUnitModel {
    private Long id ;

    private Long deviceId;

    private String unitName;

    private String unitVersion;

    private Long unitNumber;

    private String unitMaterial;

    private String remark;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
