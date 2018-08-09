package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevRepairMapper;
import com.youngc.pipeline.model.DevRepairModel;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.service.pipeline.DevRepairService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class DevRepairImpl implements DevRepairService {
    @Autowired
    DevRepairMapper devRepairMapper;

    /**
     * 分页获取设备备件
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page p =  (Page)devRepairMapper.getList(deviceId);
        return p;
    }

    /**
     * 根据id查询设备备件
     * @param id
     * @return
     */
    public DevRepairModel getInfo(Long id) {
        return devRepairMapper.getInfo(id);
    }

    /**
     * 修改设备标准参数信息
     * @param devRepairModel
     * @return
     */
    public DevRepairModel updateInfo(DevRepairModel devRepairModel) {
        devRepairMapper.updateInfo(devRepairModel);
        return devRepairModel;
    }

    /**
     * 添加设备标准参数信息
     * @param devRepairModel
     * @return
     */
    public DevRepairModel insert(DevRepairModel devRepairModel) {
        devRepairMapper.insert(devRepairModel);
        return devRepairModel;
    }

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devRepairMapper.delete(idList);
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
        List<DevRepairModel> data=new ArrayList<DevRepairModel>();
        FileInputStream input=null;
        try{
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
                        DevRepairModel devRepairModel=new DevRepairModel();
                        devRepairModel.setDeviceId(devId);
                        devRepairModel.setManufactor(row.getCell(0)!=null?"'"+row.getCell(0).getStringCellValue()+"'":null);
                        devRepairModel.setModel(row.getCell(1)!=null?"'"+row.getCell(1).getStringCellValue()+"'":null);
                        devRepairModel.setSpecification(row.getCell(2)!=null?"'"+row.getCell(2).getStringCellValue()+"'":null);
                        devRepairModel.setMaterial(row.getCell(3)!=null?"'"+row.getCell(3).getStringCellValue()+"'":null);
                        devRepairModel.setCompany(row.getCell(4)!=null?"'"+row.getCell(4).getStringCellValue()+"'":null);
                        devRepairModel.setBrand(row.getCell(5)!=null?"'"+row.getCell(5).getStringCellValue()+"'":null);
                        devRepairModel.setStock(row.getCell(6)!=null?"'"+Long.parseLong(new java.text.DecimalFormat("0").format(row.getCell(6).getNumericCellValue()))+"'":null);
                        devRepairModel.setQuantity(row.getCell(7)!=null?"'"+Long.parseLong(new java.text.DecimalFormat("0").format(row.getCell(7).getNumericCellValue()))+"'":null);
                        devRepairModel.setCycle(row.getCell(8)!=null?"'"+Long.parseLong(new java.text.DecimalFormat("0").format(row.getCell(8).getNumericCellValue()))+"'":null);
                        devRepairModel.setPrice(row.getCell(9)!=null?"'"+Double.parseDouble(new java.text.DecimalFormat("0.00").format(row.getCell(9).getNumericCellValue()))+"'":null);
                        data.add(devRepairModel);
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
        devRepairMapper.readExcel(data,user.getUserId(),devId);
        return true;
    }

    /**
     * 导出excel文件
     * @param request
     * @param response
     * @param devId
     * @return
     */
   public String downloadFileInfo(HttpServletRequest request, HttpServletResponse response, Long devId){
       List<DevRepairModel> devRepairModels=devRepairMapper.getExcel(devId);
       Workbook wb=new XSSFWorkbook();
       Sheet sheet=wb.createSheet();
       Row headRow=sheet.createRow(0);
       headRow.createCell(0).setCellValue("厂家");
       headRow.createCell(1).setCellValue("型号");
       headRow.createCell(2).setCellValue("规格");
       headRow.createCell(3).setCellValue("材质");
       headRow.createCell(4).setCellValue("单位");
       headRow.createCell(5).setCellValue("品牌");
       headRow.createCell(6).setCellValue("库存");
       headRow.createCell(7).setCellValue("数量");
       headRow.createCell(8).setCellValue("周期");
       headRow.createCell(9).setCellValue("价格");
       for(int i=0;i<devRepairModels.size();i++){
            Row dataRow=sheet.createRow(i+1);
            dataRow.createCell(0).setCellValue(devRepairModels.get(i).getManufactor());
            dataRow.createCell(1).setCellValue(devRepairModels.get(i).getModel());
            dataRow.createCell(2).setCellValue(devRepairModels.get(i).getSpecification());
            dataRow.createCell(3).setCellValue(devRepairModels.get(i).getMaterial());
            dataRow.createCell(4).setCellValue(devRepairModels.get(i).getCompany());
            dataRow.createCell(5).setCellValue(devRepairModels.get(i).getBrand());
            dataRow.createCell(6).setCellValue(devRepairModels.get(i).getStock());
            dataRow.createCell(7).setCellValue(devRepairModels.get(i).getQuantity());
            dataRow.createCell(8).setCellValue(devRepairModels.get(i).getCycle());
            dataRow.createCell(9).setCellValue(devRepairModels.get(i).getPrice());
       }
       try {
           response.setHeader("Content-type", "application/x-download");
           response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("管道配件信息.xlsx", "UTF-8"));
           OutputStream out=response.getOutputStream();
           wb.write(out);
       }catch (Exception e){
           return "导出失败";
       }
       return "导出成功";
    }
}
