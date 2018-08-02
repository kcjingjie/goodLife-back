package com.youngc.pipeline.service.pipeline;


import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.model.FileModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liweiqiang
 */
public interface FileService {

    List<TreeNode> getOrgUnitTree(String droleIds);

    List<FileModel> getFileInfo(String orgId, String unitId, String devId);

    String addfolder(FileModel fileModel);

    boolean deleteFileInfo(String fileId, String fileName, String type);

    String uploadFileFtpInfo(String folderId, Long devId, Long userId, MultipartFile file);

    String uploadFileInfo(String folderId, Long devId, Long userId, MultipartFile file);

    List<FileModel> getFolderFileInfo(Long fileId);

    String downloadFileInfo(HttpServletRequest request, HttpServletResponse response,
                             String fileName,  String filePath);

    List<FileModel> getImageFilePath(Long devId);

    String downloadImgInfo(HttpServletRequest request, HttpServletResponse response,
                           String fileName,  String filePath);

    String upImgInfo(String folderId,Long devId, Long userId, MultipartFile file);
}
