package com.youngc.pipeline.controller.pipeline;

import com.youngc.pipeline.utils.FtpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ftp")
@Slf4j
public class FtpController {

    @Autowired
    private FtpUtil ftpUtil;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap();
        map.put("code", "400");
        map.put("msg", "上传文件失败");
       // String fileName = file.getOriginalFilename();
       // log.info("上传文件:{}", fileName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());
        // 1. 取原始文件名
        String oldName = file.getOriginalFilename();
        String suffix = oldName.substring(oldName.lastIndexOf(".") + 1, oldName.length());
        // 2. ftp 服务器的文件名
        String newName = date + "." + suffix;
        InputStream inputStream = file.getInputStream();
        String filePath = null;
        Boolean flag = ftpUtil.uploadFile(newName, inputStream);
        if (flag == true) {
            log.info("上传文件成功!");
            filePath = ftpUtil.FTP_BASEPATH + newName;
            map.put("code", "200");
            map.put("msg", "上传文件成功");
        }
        map.put("path", filePath);
        return map; //该路径图片名称，前端框架可用ngnix指定的路径+filePath,即可访问到ngnix图片服务器中的图片
    }
}
