package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class ImageMarkBean {
    private Long id;

    private Long imageId;

    private Double axisX;

    private Double axisY;

    private String remark;
}
