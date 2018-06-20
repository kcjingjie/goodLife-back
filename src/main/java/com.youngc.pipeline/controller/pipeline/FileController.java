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


    @ApiOperation("添加文件夹信息")
    @PostMapping
    public Result postFolder(@RequestBody FileBean fileBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");


        Long devId = Long.parseLong(fileBean.getDevId().split("_")[1]);
        FileModel fileModel = new FileModel();
        fileModel.setFileName(fileBean.getFileName());
        fileModel.setDevId(devId);
        System.out.println(fileBean.getFolderId());
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
    public String uploadFileInfo(@RequestParam String folderId, @RequestParam String devId, @RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        FileBean fileBean = new FileBean();

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffixName);
        // 文件上传后的路径
        String filePath = "E://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        fileBean.setDevId(devId);
        fileBean.setFileName(fileName);
        fileBean.setFolderId(folderId);
        fileBean.setFilePath(filePath);
        if(suffixName.equals(".jpg")||suffixName.equals(".jpeg")||suffixName.equals(".png")||suffixName.equals(".bmp")){
            fileBean.setType("2");
        }else if(suffixName.equals(".pdf")){
            fileBean.setType("1");
        }else if(suffixName.equals(".docx")||suffixName.equals(".doc")){
            fileBean.setType("4");
        }else if(suffixName.equals(".xls")||suffixName.equals(".xlsx")){
            fileBean.setType("5");
        }
        try {
            postFolder(fileBean);
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @ApiOperation("下载文件信息")
    @PostMapping("/download")
    public String downloadFileInfo(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) {
        String fileName = "FileUploadTests.java";
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = request.getServletContext().getRealPath(
                    "//WEB-INF//");
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
                    System.out.println("success");
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
        return null;
    }
}
