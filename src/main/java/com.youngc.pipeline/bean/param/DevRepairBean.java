package com.youngc.pipeline.bean.param;

import lombok.Data;

@Data
public class DevRepairBean {

    private  Long id;

    private Long deviceId;

    private  String manufactor;

    private  String model;

    private String specification;

    private String material;

    private String company;

    private String brand;

    private String stock;

    private  String quantity;

    private String cycle;

    private String price;
}
