package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.bean.param.ImageMarkBean;
import com.youngc.pipeline.bean.param.PipeInfoBean;
import com.youngc.pipeline.model.ImageMarkModel;
import com.youngc.pipeline.model.PipeInfoModel;
import com.youngc.pipeline.result.Result;
import com.youngc.pipeline.result.ResultCode;
import com.youngc.pipeline.result.ResultGenerator;
import com.youngc.pipeline.service.pipeline.ImageService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Result uploadFileInfo( @RequestParam MultipartFile file) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        return ResultGenerator.generate(ResultCode.SUCCESS, imageService.uploadFileInfo(user.getUserId(), file));

    }

    @GetMapping("/getList")
    public Result getFolderFileInfo() {
        return ResultGenerator.generate(ResultCode.SUCCESS, imageService.getList());
    }

    @DeleteMapping
    public Result deleteFileInfo(@RequestParam("id") Long id, @RequestParam("imageName") String imageName, @RequestParam("imageUrl") String imageUrl) {
        return ResultGenerator.generate(ResultCode.SUCCESS, imageService.delete(id, imageName, imageUrl));
    }

    /**
     * 存储图片标注信息
     */
    @PostMapping("/postImageMark")
    public Result postMarkInfo(@RequestBody ImageMarkBean imageMarkBean) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        ImageMarkModel imageMarkModel = new ImageMarkModel();

        imageMarkModel.setImageId(imageMarkBean.getImageId());
        imageMarkModel.setAxisX(imageMarkBean.getAxisX());
        imageMarkModel.setAxisY(imageMarkBean.getAxisY());
        imageMarkModel.setRemark(imageMarkBean.getRemark());

        imageMarkModel.setAddPerson(user.getUserId());
        imageMarkModel.setLastPerson(user.getUserId());

        return ResultGenerator.generate(ResultCode.SUCCESS, imageService.postImageMark(imageMarkModel));
    }

    /**
     * 查询图片标注信息列表
     * @param imageId
     * @return
     */
    @GetMapping("/getImageMarkList")
    public Result getMarkList(Long imageId){
        return ResultGenerator.generate(ResultCode.SUCCESS,imageService.getMarkList(imageId));
    }


}
