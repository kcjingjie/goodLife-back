package com.youngc.pipeline.service.pipeline.impl;


import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.bean.param.FileBean;
import com.youngc.pipeline.controller.pipeline.FileController;
import com.youngc.pipeline.mapper.pipeline.FileMapper;
import com.youngc.pipeline.mapper.system.OrgMapper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.service.pipeline.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OrgMapper orgMapper;
    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;

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
     * 添加文件信息
     */
    public boolean addfolder(FileModel fileModel) {
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
                file.delete();
            } else {
                delFolder(filePath);
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
     * 上传文件
     */
    public String uploadFileInfo(String folderId, Long devId, Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件为空";
        }
        String devName = "";
        String folderName = "";
        // 文件上传后的路径
        String filePath = "E://pipeline//";
        devName = fileMapper.getDevNameByDevId(devId);
        if (Long.parseLong(folderId) != 0) {
            FileModel fileModel = fileMapper.getFileNameByFileId(Long.parseLong(folderId));
            folderName = fileModel.getFileName();
            // 文件上传后的路径
            filePath = filePath + devName + "//" + folderName + "//";
        } else {
            // 文件上传后的路径
            filePath = filePath + devName + "//";
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
            addfolder(fileModel);
            file.transferTo(dest);
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
        if (fileName != null) {
            File file = new File(filePath, fileName);
            if (file.exists()) {
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
                    byte[] buffer = new byte[1024];


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
        return null;
    }
}
