package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author liuyan
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryRecordModel {

    private Long id;

    private String addPerson;

    private String addTime;

    private String description;

    private Long deviceId;

}
