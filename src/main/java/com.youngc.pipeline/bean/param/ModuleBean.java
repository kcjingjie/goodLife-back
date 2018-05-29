package com.youngc.pipeline.bean.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleBean {

    private Long moduleId;

    private Long userId;

    private Long pid;

    private String moduleName;

    private String controlId;

    private String modulePath;

    private String moduleDesc;

    private Long type;

    private Long status;

    private Long level;

    private Long priority;

    private String icon;

    private Long addPerson;

    private Date addTime;

    private Long lastPerson;

    private Date lastTime;
}
