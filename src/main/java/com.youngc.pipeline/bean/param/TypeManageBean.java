package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class TypeManageBean {
    private Long modelId;

    private String modelCode;

    private String modelName;

    private Long modelType;

    private String modelEquip;

    private String modelDesc;

    private Long status;

    private String addPerson;

    private Date addDate;

    private String lastPerson;

    private Date lastTime;
}
