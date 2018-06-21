package com.youngc.pipeline.bean.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileBean {

    private String fileName;

    private String folderId;

    private String devId;

    private String type;

    private String devName;

}
