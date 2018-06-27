package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevModelMonParaBean {
    private  Long id;

    private Long modelId;

    private String modelName;

    private String paraName;

    private String paraId;

    private int paraDataType;

    private String dataName;

    private String paraUnit;

    private int paraType;

    private String typeName;

    private String remark;
}
