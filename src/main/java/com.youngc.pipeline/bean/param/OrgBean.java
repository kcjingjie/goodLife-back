package com.youngc.pipeline.bean.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgBean {

    private Long orgId;

    private Long pid;

    private String orgCode;

    private String orgName;

    private String orgDesc;

}
