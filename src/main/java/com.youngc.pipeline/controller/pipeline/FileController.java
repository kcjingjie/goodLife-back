package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.FileBean;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.FileService;
import com.youngc.pipeline.utils.FtpUtil;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * @author
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private FileService fileService;

    @ApiOperation("获取组织单位设备树")
    @GetMapping("/orgUnitDevTree")
    public Result getOrgUnitTree() {

        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.getOrgUnitTree());
    }

    @ApiOperation("获取文件信息")
    @GetMapping
    public Result getFileInfo(@RequestParam String orgId, @RequestParam String unitId, @RequestParam String devId) {
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.getFileInfo(orgId, unitId, devId));
    }

    @ApiOperation("获取文件夹下文件信息")
    @GetMapping("/folder")
    public Result getFolderFileInfo(@RequestParam Long fileId) {
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.getFolderFileInfo(fileId));
    }


    /**
     * 新建文件夹
     *
     * @param fileBean
     * @return
     */
    @ApiOperation("添加文件信息")
    @PostMapping
    public Result postFolder(@RequestBody FileBean fileBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");

        Long devId = Long.parseLong(fileBean.getDevId().split("_")[1]);
        FileModel fileModel = new FileModel();
        fileModel.setFileName(fileBean.getFileName());
        fileModel.setDevId(devId);
        fileModel.setFolderId(Long.parseLong(fileBean.getFolderId()));
        fileModel.setUserId(user.getUserId());
        fileModel.setType(fileBean.getType());
        fileModel.setDevName(fileBean.getDevName());
        //String filePath = "E://pipeline//" + fileBean.getDevName() + "//" + fileModel.getFileName();
        String filePath = "/file/" + fileBean.getDevName() + "/";
        fileModel.setFilePath(filePath);


//        File dest = new File(filePath );
//        // 检测是否存在目录
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.addfolder(fileModel));
    }


    @ApiOperation("删除文件信息")
    @DeleteMapping
    public Result deleteFileInfo(@RequestParam String fileId, @RequestParam String fileName, @RequestParam String type) {
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.deleteFileInfo(fileId, fileName, type));
    }

    @ApiOperation("上传文件信息")
    @PostMapping("/upload")
    public Result uploadFileInfo(@RequestParam String folderId, @RequestParam String devId, @RequestParam MultipartFile file) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        Long devIds = Long.parseLong(devId.split("_")[1]);
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.uploadFileInfo(folderId, devIds, user.getUserId(), file));

    }

    @ApiOperation("下载文件信息")
    @GetMapping("/download")
    public String downloadFileInfo(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String fileName, @RequestParam String filePath) {
        return fileService.downloadFileInfo(request, response, fileName, filePath);
    }

    /**
     * 上传单管图
     *
     * @param devId
     * @param file
     * @return
     */
    @PostMapping("/upImage")
    public Result uploadImageInfo(@RequestParam String folderId, @RequestParam String devId, @RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        Long devIds = Long.parseLong(devId.split("_")[1]);
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.upImageInfo(folderId, devIds, user.getUserId(), file, request, response));

    }
}
