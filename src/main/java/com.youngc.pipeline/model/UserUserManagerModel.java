package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserUserManagerModel {

    private Long id;

    private String user_name;

    private String password;

    private String real_name;

    private Long add_person;

    private Date add_time;

    private Long last_person;

    private Date last_time;
}
