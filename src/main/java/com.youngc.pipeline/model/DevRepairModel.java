package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DevRepairModel {

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

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
