package com.youngc.pipeline.mapper.pipeline;

import com.youngc.pipeline.model.FileModel;
import com.youngc.pipeline.model.ImageModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ImageMapper {

    @Select(" INSERT INTO sys_image(image_name,image_url,add_person,add_time,last_person,last_time)" +
            " VALUES(#{imageName},#{imageUrl},#{addPerson},now(),#{addPerson},now());")
    FileModel postFolder(ImageModel imageModel);

    @Select(" SELECT id,image_name,image_url FROM sys_image ")
    List<ImageModel> getList();

    @Select(" DELETE FROM sys_image WHERE id = #{id} ")
    void delete(@Param("id")Long id);
}
