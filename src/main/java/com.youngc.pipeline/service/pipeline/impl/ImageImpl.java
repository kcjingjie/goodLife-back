package com.youngc.pipeline.service.pipeline.impl;

import com.youngc.pipeline.mapper.pipeline.ImageMapper;
import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.model.ImageMarkModel;
import com.youngc.pipeline.model.ImageModel;
import com.youngc.pipeline.service.pipeline.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ImageImpl implements ImageService{
    @Autowired
    private ImageMapper imageMapper;
    public String uploadFileInfo(Long userId, MultipartFile file) {
        if (file.isEmpty()) {
            return "上传文件为空";
        }
        // 文件上传后的路径
       String filePath = "E://pipelineImage//";
        //String filePath="http://localhost:8080/pipeline/";
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
        ImageModel imageModel = new ImageModel();

        imageModel.setImageName(fileName);
        imageModel.setImageUrl(filePath);
        imageModel.setAddPerson(userId);

        try {
            addfolder(imageModel);
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    public boolean addfolder(ImageModel imageModel) {
        imageMapper.postFolder(imageModel);
        return true;
    }

    public List<ImageModel> getList() {
        return imageMapper.getList();
    }

    public boolean delete(Long id, String imageName, String imageUrl) {
        try {

            File file = new File(imageUrl + imageName);
            file.delete();
            imageMapper.delete(id);
        } catch (Exception e) {
            System.out.println("Exception occured");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 存储图片标注信息
     * @param imageMarkModel
     * @return
     */
    public ImageMarkModel postImageMark(ImageMarkModel imageMarkModel) {
        imageMapper.postImageMark(imageMarkModel);
        return imageMarkModel;
    }

    /**
     * 获取图片标注信息列表
     * @param imageId
     * @return
     */
    public List<ImageMarkModel> getMarkList(String imageId) {
        return imageMapper.getMarkList(imageId);
    }


}
