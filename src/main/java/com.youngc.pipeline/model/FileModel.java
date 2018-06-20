package com.youngc.pipeline.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author liweiqiang
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileModel {

    private Long fileId;

    private Long fileName;

    private Long devId;

    private String type;

    private String filePath;

    private String lastTime;

}
