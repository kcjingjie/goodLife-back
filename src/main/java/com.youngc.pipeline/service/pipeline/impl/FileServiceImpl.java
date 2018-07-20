package com.youngc.pipeline.service.pipeline.impl;


import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.bean.param.FileBean;
import com.youngc.pipeline.controller.pipeline.FileController;
import com.youngc.pipeline.mapper.pipeline.FileMapper;
import com.youngc.pipeline.mapper.system.OrgMapper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.service.pipeline.FileService;
import com.youngc.pipeline.utils.FileUtil;
import com.youngc.pipeline.utils.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;

    @Autowired
    private FtpUtil ftpUtil;

    /**
     * 查询组织单位设备树
     */
    public List<TreeNode> getOrgUnitTree() {

        List<Map> org = orgMapper.getTree();
        List<Map> unit = sysDataRoleMapper.getOrgUnitTree();
        List<Map> dev = fileMapper.getDevTree();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < org.size(); i++) {
            if (((Integer) (org.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId("org_" + ((Integer) (org.get(i).get("org_id"))).toString());
                root.setName((String) (org.get(i).get("org_name")));
                root.setChildren(getChilds(unit, org, dev, ((Integer) (org.get(i).get("org_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> units, List<Map> orgs, List<Map> dev, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (int j = 0; j < orgs.size(); j++) {

            Iterator<Map> unit = units.iterator();

            while (unit.hasNext()) {
                Map unitValue = unit.next();
                TreeNode node = new TreeNode();
                if (unitValue.get("org_id").equals(parentId)) {
                    node.setId("unit_" + ((Integer) (unitValue.get("unit_id"))).toString());
                    node.setName((String) (unitValue.get("unit_name")));
                    node.setChildren(getUnitChilds(dev, (Integer) (unitValue.get("unit_id"))));
                    children.add(node);
                    unit.remove();
                }
            }
            if ((orgs.get(j).get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId("org_" + ((Integer) (orgs.get(j).get("org_id"))).toString());
                node.setName((String) (orgs.get(j).get("org_name")));
                node.setChildren(getChilds(units, orgs, dev, (Integer) (orgs.get(j).get("org_id"))));
                children.add(node);
            }

        }
        return children;
    }

    List<TreeNode> getUnitChilds(List<Map> dev, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        Iterator<Map> devs = dev.iterator();

        while (devs.hasNext()) {
            Map devValue = devs.next();
            TreeNode node = new TreeNode();
            if (devValue.get("unit_id").equals(parentId)) {
                node.setId("dev_" + ((Integer) (devValue.get("device_id"))).toString());
                node.setName((String) (devValue.get("device_name")));
                children.add(node);
                devs.remove();
            }
        }
        return children;
    }

    /**
     * 查询文件信息
     */
    public List<FileModel> getFileInfo(String orgId, String unitId, String devId) {
        String deviceIds = "";

        if (orgId.equals("0") && unitId.equals("0")) {
            String[] devIdArr = devId.split("_");
            deviceIds = devIdArr[1];

        } else if (orgId.equals("0") && devId.equals("0")) {
            String[] unitIdArr = unitId.split("_");
            deviceIds = fileMapper.getDevIdByUnitId(unitIdArr[1]);

        } else {
            String[] orgIdArr = orgId.split("_");
            Long org = Long.parseLong(orgIdArr[1]);
            String unitIds = fileMapper.getUnitIdByOrgId(org);
            if (unitIds != null) {
                deviceIds = fileMapper.getDevIdByUnitId(unitIds);
            }
        }
        if (deviceIds != null && deviceIds != "") {
            return fileMapper.getFileInfoByDevId(deviceIds);
        } else {
            return null;
        }
    }

    /**
     * 添加文件夹信息
     */
    public boolean addfolder(FileModel fileModel) {
        ftpUtil.mkdir(fileModel.getFilePath(), fileModel.getFileName());
        fileMapper.postFolder(fileModel);
        return true;
    }

    public boolean addfile(FileModel fileModel) {
        //ftpUtil.mkdir(fileModel.getFilePath(), fileModel.getFileName());
        fileMapper.postFolder(fileModel);
        return true;
    }

    /**
     * 删除档案信息
     */
    public boolean deleteFileInfo(String fileId, String fileName, String type) {
        try {
            FileModel fileModel = fileMapper.getFileNameByFileId(Long.parseLong(fileId));
            String filePath = fileModel.getFilePath();
            if (Integer.parseInt(type) != 3) {
                File file = new File(filePath + fileName);
                //file.delete();
                ftpUtil.deleteFile(filePath, fileName);
            } else {
                ftpUtil.rmdir(filePath, fileName);
                //delFolder(filePath);
            }
            fileMapper.deleteFile(fileId);
        } catch (Exception e) {
            System.out.println("Exception occured");
            e.printStackTrace();
        }
        return true;
    }

    //删除文件夹
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除文件夹下所有文件
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 查询文件夹下文件信息
     */
    public List<FileModel> getFolderFileInfo(Long fileId) {
        return fileMapper.getFolderFileInfo(fileId);
    }

    /**
     * 上传文件至相对路径
     * @param folderId
     * @param devId
     * @param userId
     * @param file
     * @return
     */
    public String uploadFileInfo(String folderId, Long devId, Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件为空";
        }
        String devName = "";
        String folderName = "";
        // 文件上传后的路径
        String filePath = "/file/";
        devName = fileMapper.getDevNameByDevId(devId);
        if (Long.parseLong(folderId) != 0) {
            FileModel fileModel = fileMapper.getFileNameByFileId(Long.parseLong(folderId));
            folderName = fileModel.getFileName();
            // 文件上传后的路径
            filePath = filePath + devName + "/" + folderName + "/";
        } else {
            // 文件上传后的路径
            filePath = filePath + devName + "/";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FileModel fileModel = new FileModel();
        if (suffixName.equals(".jpg") || suffixName.equals(".jpeg") || suffixName.equals(".png") || suffixName.equals(".bmp")) {
            fileModel.setType("2");
        } else if (suffixName.equals(".pdf")) {
            fileModel.setType("1");
        } else if (suffixName.equals(".docx") || suffixName.equals(".doc")) {
            fileModel.setType("4");
        } else if (suffixName.equals(".xls") || suffixName.equals(".xlsx")) {
            fileModel.setType("5");
        }
        fileModel.setFileName(fileName);
        fileModel.setDevId(devId);
        fileModel.setFolderId(Long.parseLong(folderId));
        fileModel.setUserId(userId);
        fileModel.setFilePath(filePath);

        try {
            addfile(fileModel);
            ftpUtil.upFile(fileName, file.getInputStream(), filePath);
            // file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 下载文件
     */
    public String downloadFileInfo(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String fileName, @RequestParam String filePath) {
        String str = null;
        try {
            response.setHeader("Content-type", "application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            //ftpUtil.downloadFile(filePath, fileName, response.getOutputStream());
            str = FileUtil.downloadFile(response,filePath,fileName);
        } catch (IOException e) {
            str = "操作失败";
            e.printStackTrace();
        }
        return str;
    }
    /**
     * 获取单管图图片路径
     *
     * @param devId
     * @return
     */
    public List<FileModel> getImageFilePath(Long devId) {
        return fileMapper.getImageFilePath(devId);
    }

    /**
     * 加载图片
     *
     * @param request
     * @param response
     * @param fileName
     * @param filePath
     * @return
     */
    public String downloadImgInfo(HttpServletRequest request, HttpServletResponse response, String fileName, String filePath) {
        response.setHeader("Content-type", "image/jpeg");
        return FileUtil.downloadFile(response,filePath,fileName);
    }

    /**
     * 上传单管图至相对路径
     *
     * @param folderId
     * @param devId
     * @param userId
     * @param file
     * @return
     */
    public String upImgInfo(String folderId, Long devId, Long userId, MultipartFile file) {
        try {
        String filePath = null;// 文件路径
            String sqlPath="";
        if (file.isEmpty()) {
            return "上传文件为空";
        }
        // 项目在容器中实际发布运行的根路径
        String realPath = new File(ResourceUtils.getURL("src/main/resources/files").getPath()).getAbsolutePath();
        // 获取文件名
        String fileName = file.getOriginalFilename();

        String devName = fileMapper.getDevNameByDevId(devId);
        if (Long.parseLong(folderId) != 0) {
            FileModel fileModel = fileMapper.getFileNameByFileId(Long.parseLong(folderId));
            String folderName = fileModel.getFileName();
            // 文件上传后的路径
            filePath = realPath + "/" + devName + "/" + folderName + "/";
            sqlPath="/files/" + devName + "/" + folderName + "/";
        } else {
            // 文件上传后的路径
            filePath = realPath + "/" + devName + "/";
            sqlPath="/files/" + devName + "/";
        }
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        System.out.println("dest:" + dest);
        FileModel fileModel = new FileModel();

        fileModel.setType("6");

        fileModel.setFileName(fileName);
        fileModel.setDevId(devId);
        fileModel.setFolderId(Long.parseLong("0"));
        fileModel.setUserId(userId);
        fileModel.setFilePath(sqlPath);

            addfile(fileModel);
            file.transferTo(dest);
            return "上传成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 上传文件至ftp
     */
    public String uploadFileFtpInfo(String folderId, Long devId, Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件为空";
        }
        String devName = "";
        String folderName = "";
        // 文件上传后的路径
        String filePath = "/file/";
        devName = fileMapper.getDevNameByDevId(devId);
        if (Long.parseLong(folderId) != 0) {
            FileModel fileModel = fileMapper.getFileNameByFileId(Long.parseLong(folderId));
            folderName = fileModel.getFileName();
            // 文件上传后的路径
            filePath = filePath + devName + "/" + folderName + "/";
        } else {
            // 文件上传后的路径
            filePath = filePath + devName + "/";
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        FileModel fileModel = new FileModel();
        if (suffixName.equals(".jpg") || suffixName.equals(".jpeg") || suffixName.equals(".png") || suffixName.equals(".bmp")) {
            fileModel.setType("2");
        } else if (suffixName.equals(".pdf")) {
            fileModel.setType("1");
        } else if (suffixName.equals(".docx") || suffixName.equals(".doc")) {
            fileModel.setType("4");
        } else if (suffixName.equals(".xls") || suffixName.equals(".xlsx")) {
            fileModel.setType("5");
        }
        fileModel.setFileName(fileName);
        fileModel.setDevId(devId);
        fileModel.setFolderId(Long.parseLong(folderId));
        fileModel.setUserId(userId);
        fileModel.setFilePath(filePath);

        try {
            addfile(fileModel);
            ftpUtil.upFile(fileName, file.getInputStream(), filePath);
            // file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
}
