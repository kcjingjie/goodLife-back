package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class DevLeakBean {
    private Long id;

    private Long devId;

    private String leakNo;

    private String deviceName;

    private String leakDegree;

    private String occurTime;

    private String planExeTime;

    private String handleMethod;

    private String remark;
}
