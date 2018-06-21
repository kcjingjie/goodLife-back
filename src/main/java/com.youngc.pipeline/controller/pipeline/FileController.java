package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.FileBean;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.FileService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


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
        fileModel.setFilePath(fileBean.getFilePath());

        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.addfolder(fileModel));
    }


    @ApiOperation("删除文件信息")
    @DeleteMapping
    public Result deleteFileInfo(@RequestParam String fileId, @RequestParam String type) {
        return ResultGenerator.generate(ResultCode.SUCCESS, fileService.deleteFileInfo(fileId, type));
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
    public Result downloadFileInfo(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
        if (fileName != null) {
            //当前是从该工程的E://test//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = request.getServletContext().getRealPath(
                    "E://test//");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultGenerator.generate(ResultCode.SUCCESS);
    }
}
