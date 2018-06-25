package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevConfigParaBean {
    private  Long id;

    private Long deviceId;

    private String deviceName;

    private String paraName;

    private String paraId;

    private String paraValue;

    private String paraUnit;

    private int paraType;

    private String remark;
}
