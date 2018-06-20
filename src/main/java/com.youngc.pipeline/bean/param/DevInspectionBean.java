package com.youngc.pipeline.bean.param;

import lombok.Data;

import java.util.Date;

@Data
public class DevInspectionBean {
    private Long id;

    private Long devId;

    private Date exeTime;

    private Long exeCycle;

    private Long userId;

    private String exeDesc;

    private String remark;
}
