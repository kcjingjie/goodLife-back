package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImageModel {
    private Long id;

    private String imageName;

    private String imageUrl;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
