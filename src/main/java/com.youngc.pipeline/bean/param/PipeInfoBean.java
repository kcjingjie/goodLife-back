package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class PipeInfoBean {
    private Long deviceId;

    private Long unitId;

    private Long modelId;

    private Long imageId;

    private String deviceAlias;

    private String deviceName;

    private String deviceCode;

    private String customCode;

    private String deviceDesc;

    private int status;

    private String address;

}
