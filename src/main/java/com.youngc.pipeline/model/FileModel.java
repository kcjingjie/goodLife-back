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

    private String fileName;

    private Long devId;

    private Long folderId;

    private String type;

    private String filePath;

    private String devName;

    private Long userId;

    private String lastTime;

}
