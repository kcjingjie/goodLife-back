package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevModelConfigParaModel {
    private  Long id;

    private Long modelId;

    private String modelName;

    private String paraName;

    private String paraId;

    private String paraValue;

    private String paraUnit;

    private int paraType;

    private String typeName;

    private String remark;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
