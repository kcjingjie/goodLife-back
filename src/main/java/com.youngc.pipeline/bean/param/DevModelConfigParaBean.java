package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevModelConfigParaBean {
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
}
