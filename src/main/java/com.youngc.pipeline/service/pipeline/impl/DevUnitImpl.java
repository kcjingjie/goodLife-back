package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevUnitMapper;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.service.pipeline.DevUnitService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DevUnitImpl implements DevUnitService {

    @Autowired
    private DevUnitMapper devUnitMapper;

    /**
     * 分页获取设备管件信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize) ;
        return (Page)devUnitMapper.getList(deviceId);
    }

    /**
     * 根据id查询设备管件信息
     * @param id
     * @return
     */
    public DevUnitModel getInfo(Long id) {
        return devUnitMapper.getInfo(id);
    }

    /**
     * 修改设备管件信息
     * @param devUnitModel
     * @return
     */
    public DevUnitModel updateInfo(DevUnitModel devUnitModel) {
        devUnitMapper.updateInfo(devUnitModel);
        return devUnitModel;
    }

    /**
     * 添加设备管件信息
     * @param devUnitModel
     * @return
     */
    public DevUnitModel insert(DevUnitModel devUnitModel) {
        devUnitMapper.insert(devUnitModel);
        return devUnitModel;
    }

    /**
     * 删除设备管件信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devUnitMapper.delete(idList);
        return true;
    }

    /**
     * 读取Excel数据
     * @param file
     * @param devId
     * @return
     */
    public boolean readExcel(Long devId,MultipartFile file){
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        List<DevUnitModel> data=new ArrayList<DevUnitModel>();
        FileInputStream input=null;
        try {
            input = (FileInputStream)file.getInputStream();
            Workbook workBook = WorkbookFactory.create(input);
            int numberOfSheets = workBook.getNumberOfSheets();
            for (int s = 0; s < numberOfSheets; s++) { // sheet工作表
                Sheet sheetAt = workBook.getSheetAt(s);
                int rowsOfSheet = sheetAt.getPhysicalNumberOfRows(); // 获取当前Sheet的总列数
                for (int r = 1; r < rowsOfSheet; r++) { // 总行
                    Row row = sheetAt.getRow(r);
                    if (row == null) {
                        continue;
                    } else {

                        DevUnitModel devUnitModel=new DevUnitModel();
                        devUnitModel.setUnitName("'"+row.getCell(0).getStringCellValue()+"'");
                        devUnitModel.setUnitVersion("'"+row.getCell(1).getStringCellValue()+"'");
                        double d=row.getCell(2).getNumericCellValue();
                        devUnitModel.setUnitNumber(Long.parseLong(new java.text.DecimalFormat("0").format(d)));
                        if(row.getCell(3)!=null){
                        devUnitModel.setUnitMaterial("'"+row.getCell(3).getStringCellValue()+"'");
                        }
                        data.add(devUnitModel);
                        }
                    }
                }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (input!= null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        devUnitMapper.readExcel(data,user.getUserId(),devId);
        return true;
    }
}
