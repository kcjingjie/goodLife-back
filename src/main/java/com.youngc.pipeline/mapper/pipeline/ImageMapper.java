package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.model.ImageMarkModel;
import com.youngc.pipeline.model.ImageModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImageMapper {
    /**
     * 添加图片信息
     * @param imageModel
     * @return
     */
    @Select(" INSERT INTO sys_image(image_name,image_url,add_person,add_time,last_person,last_time)" +
            " VALUES(#{imageName},#{imageUrl},#{addPerson},now(),#{addPerson},now());")
    FileModel postFolder(ImageModel imageModel);

    /**
     * 查询图片列表
     * @return
     */
    @Select(" SELECT id,image_name,image_url FROM sys_image ")
    List<ImageModel> getList();

    /**
     * 删除图片
     * @param id
     */
    @Select(" DELETE FROM sys_image WHERE id = #{id} ")
    void delete(@Param("id")Long id);

    /**
     * 存储图片标注信息
     */
    @Insert(" INSERT INTO sys_image_mark(image_id,axis_x,axis_y,remark,add_person,add_time,last_person,last_time)" +
            " VALUES(#{imageId},#{axisX},#{axisY},#{remark},#{addPerson},now(),#{lastPerson},now());")
    int postImageMark(ImageMarkModel imageMarkModel);

    /**
     * 获取图片标注信息
     * @param imageId
     * @return
     */
    @Select(" SELECT id,axis_x,axis_y,remark FROM sys_image_mark where image_id=#{imageId} ")
    List<ImageMarkModel> getMarkList(@Param("imageId") Long imageId);


}
