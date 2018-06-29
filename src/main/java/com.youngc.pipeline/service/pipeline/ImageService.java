package com.youngc.pipeline.service.pipeline;

import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.model.ImageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    String uploadFileInfo(long userId, MultipartFile file);

    List<ImageModel> getList();

    boolean delete(Long id, String imageName, String imageUrl);
}
