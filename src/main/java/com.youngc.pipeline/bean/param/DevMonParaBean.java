package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class DevMonParaBean {
    private  Long id;

    private Long deviceId;

    private String deviceName;

    private String paraName;

    private String paraId;

    private String paraValueUp;

    private String paraValueDown;

    private String paraValue;

    private String paraUnit;

    private int paraType;

    private String typeName;

    private String remark;

}
