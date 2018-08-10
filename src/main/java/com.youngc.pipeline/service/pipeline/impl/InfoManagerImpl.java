package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.bean.context.TreeNode;
import com.youngc.pipeline.mapper.pipeline.DevModelConfigParaMapper;
import com.youngc.pipeline.mapper.pipeline.InfoManagerMapper;
import com.youngc.pipeline.mapper.system.OrgMapper;
import com.youngc.pipeline.mapper.system.SysDataRoleMapper;
import com.youngc.pipeline.model.*;
import com.youngc.pipeline.service.pipeline.InfoManagerService;
import com.youngc.pipeline.utils.RequestContextHolderUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Service
public class InfoManagerImpl implements InfoManagerService {
    @Autowired
    private SysDataRoleMapper sysDataRoleMapper;
    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private InfoManagerMapper infoManagerMapper;

    @Autowired
    private DevModelConfigParaMapper devModelConfigParaMapper;

    /**
     * 获取单位树
     *
     * @return
     */
    public List<TreeNode> getOrgUnitTree() {

        List<Map> org = orgMapper.getTree();
        List<Map> unit = sysDataRoleMapper.getOrgUnitTree();

        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < org.size(); i++) {
            if (((Integer) (org.get(i).get("pid"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId("org_" + ((Integer) (org.get(i).get("org_id"))).toString());
                root.setName((String) (org.get(i).get("org_name")));
                root.setChildren(getChilds(unit, org, ((Integer) (org.get(i).get("org_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    List<TreeNode> getChilds(List<Map> units, List<Map> orgs, Integer parentId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (int j = 0; j < orgs.size(); j++) {

            Iterator<Map> unit = units.iterator();

            while (unit.hasNext()) {
                Map unitValue = unit.next();
                TreeNode node = new TreeNode();
                if (unitValue.get("org_id").equals(parentId)) {
                    node.setId(((Integer) (unitValue.get("unit_id"))).toString());
                    node.setName((String) (unitValue.get("unit_name")));
                    children.add(node);
                    unit.remove();
                }
            }
            if ((orgs.get(j).get("pid")).equals(parentId)) {
                TreeNode node = new TreeNode();
                node.setId("org_" + ((Integer) (orgs.get(j).get("org_id"))).toString());
                node.setName((String) (orgs.get(j).get("org_name")));
                node.setChildren(getChilds(units, orgs, (Integer) (orgs.get(j).get("org_id"))));
                children.add(node);
            }

        }
        return children;
    }

    /**
     * 分页获取设备信息
     *
     * @param keyWord
     * @param pid
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyWord, Long pid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) infoManagerMapper.getList(keyWord, pid);
    }

    /**
     * 根据设备id查询设备信息
     *
     * @param id
     * @return
     */
    public PipeInfoModel getInfo(Long id) {
        return infoManagerMapper.getInfo(id);
    }

    /**
     * 修改设备信息
     */
    @Transactional
    public PipeInfoModel updateInfo(PipeInfoModel pipeInfoModel) {
        infoManagerMapper.updateInfo(pipeInfoModel);
       /* Long deviceId = pipeInfoModel.getDeviceId();
        Long modelId = pipeInfoModel.getModelId();
        Long personId = pipeInfoModel.getLastPerson();
        String id = deviceId+"";
        infoManagerMapper.deleteConfigPara(id);
        infoManagerMapper.deleteMonPara(id);
        infoManagerMapper.insertConfigParas(deviceId,modelId,personId);
        infoManagerMapper.insertMonParas(deviceId,modelId,personId);*/
        return pipeInfoModel;
    }

    /**
     * 添加设备信息
     *
     * @param pipeInfoModel
     * @return
     */
    public PipeInfoModel insert(PipeInfoModel pipeInfoModel) {
        infoManagerMapper.insert(pipeInfoModel);
      /*  Long deviceId = pipeInfoModel.getId();
        Long modelId = pipeInfoModel.getModelId();
        Long personId = pipeInfoModel.getLastPerson();
        infoManagerMapper.insertConfigParas(deviceId,modelId,personId);
        infoManagerMapper.insertMonParas(deviceId,modelId,personId);*/
        return pipeInfoModel;
    }

    public List getDevModelConfig(Long modelId) {
        return devModelConfigParaMapper.getList(modelId);
    }

    /**
     * 删除设备信息，同时删除设备下的监测参数与标准参数
     */
    @Transactional
    public boolean delete(String ids) {
        infoManagerMapper.delete(ids);
        infoManagerMapper.deleteConfigPara(ids);
        infoManagerMapper.deleteMonPara(ids);
        return true;
    }

    /**
     * 查询设备编号是否唯一
     *
     * @param code
     * @return
     */
    public List<PipeInfoModel> getInfoByCode(String code) {
        return infoManagerMapper.getInfoByCode(code);
    }

    /**
     * 查询设备模型id，模型名称
     *
     * @return
     */
    public List getDevModel() {
        return infoManagerMapper.getDevModel();
    }

    /**
     * 查询所有不重复的参数名称
     *
     * @return
     */
    public List<DevConfigParaModel> getParaName() {
        return infoManagerMapper.getParaName();
    }

    /**
     * 根据单位id查询标准参数信息
     *
     * @return
     */
    public List<DevConfigParaModel> getParaValue(Long unitId) {
        return infoManagerMapper.getParaValue(unitId);
    }


    /**
     * 根据id查询文件
     *
     * @param devId
     * @return
     */
    public List<TreeNode> showFile(Long devId) {
        List<Map> fileById = infoManagerMapper.getFileById(devId);
        List<TreeNode> tree = new ArrayList<TreeNode>();

        for (int i = 0; i < fileById.size(); i++) {
            if (((Integer) (fileById.get(i).get("folder_id"))) == 0) {
                TreeNode root = new TreeNode();
                root.setId(((Integer) (fileById.get(i).get("file_id"))).toString());
                root.setName((String) (fileById.get(i).get("file_name")));
                root.setPath((String) (fileById.get(i).get("file_path")));
                root.setType((String) (fileById.get(i).get("type")));
                root.setChildren(getFileChilds(fileById, ((Integer) (fileById.get(i).get("file_id")))));
                tree.add(root);
            }
        }
        return tree;
    }

    public List<TreeNode> getFileChilds(List<Map> fileById, Integer fileId) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        for (int j = 0; j < fileById.size(); j++) {

            if ((fileById.get(j).get("folder_id")).equals(fileId)) {
                TreeNode node = new TreeNode();
                node.setId(((Integer) (fileById.get(j).get("folder_id"))).toString());
                node.setName((String) (fileById.get(j).get("file_name")));
                node.setPath((String) (fileById.get(j).get("file_path")));
                node.setType((String) (fileById.get(j).get("type")));
                node.setChildren(getFileChilds(fileById, (Integer) (fileById.get(j).get("file_id"))));
                children.add(node);
            }
        }
        return children;
    }

    public List<DevConfigParaModel> getDeatail(Long devId) {
        return infoManagerMapper.getDeatail(devId);
    }

    public String excelDownload(HttpServletRequest request, HttpServletResponse response, Long unitId) {
        int j = 0;
        List<PipeInfoModel> pipeInfoModels = infoManagerMapper.excelDownload(unitId);
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row headRow = sheet.createRow(j++);
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("输送介质", 3);
        map.put("起点位置", 4);
        map.put("终点位置", 5);
        map.put("管道直径", 6);
        map.put("管道壁厚", 7);
        map.put("管道长度", 8);
        map.put("设计压力", 9);
        map.put("设计温度", 10);
        map.put("工作压力", 11);
        map.put("工作温度", 12);
        map.put("管道材料", 13);
        map.put("焊口数量", 14);
        map.put("铺设方式", 15);
        map.put("防腐方式", 16);
        map.put("管道标识", 17);
        map.put("保温绝热方式", 18);
        map.put("探伤比例", 19);
        map.put("安装竣工日期", 20);
        map.put("投用日期", 21);
        map.put("检验时间", 22);
        headRow.createCell(0).setCellValue("管线号");
        headRow.createCell(1).setCellValue("管道等级");
        headRow.createCell(2).setCellValue("所属装置");
        headRow.createCell(3).setCellValue("输送介质");
        headRow.createCell(4).setCellValue("起点位置");
        headRow.createCell(5).setCellValue("终点位置");
        headRow.createCell(6).setCellValue("管道直径");
        headRow.createCell(7).setCellValue("管道壁厚");
        headRow.createCell(8).setCellValue("管道长度");
        headRow.createCell(9).setCellValue("设计压力");
        headRow.createCell(10).setCellValue("设计温度");
        headRow.createCell(11).setCellValue("工作压力");
        headRow.createCell(12).setCellValue("工作温度");
        headRow.createCell(13).setCellValue("管道材料");
        headRow.createCell(14).setCellValue("焊口数量");
        headRow.createCell(15).setCellValue("铺设方式");
        headRow.createCell(16).setCellValue("防腐方式");
        headRow.createCell(17).setCellValue("管道标识");
        headRow.createCell(18).setCellValue("保温绝热方式");
        headRow.createCell(19).setCellValue("探伤比例");
        headRow.createCell(20).setCellValue("安装竣工日期");
        headRow.createCell(21).setCellValue("投用日期");
        headRow.createCell(22).setCellValue("检验时间");
        if (pipeInfoModels.size() == 0) {
            return "暂无数据";
        }
        Row dataRow = sheet.createRow(j++);
        dataRow.createCell(0).setCellValue(pipeInfoModels.get(0).getDeviceName());
        dataRow.createCell(1).setCellValue(pipeInfoModels.get(0).getDeviceTypeName());
        dataRow.createCell(2).setCellValue(pipeInfoModels.get(0).getDeviceEquipName());
        if (map.get(pipeInfoModels.get(0).getParaName()) != null) {
            dataRow.createCell(map.get(pipeInfoModels.get(0).getParaName())).setCellValue(pipeInfoModels.get(0).getParaValue() + pipeInfoModels.get(0).getParaUnit());
        }
        for (int i = 1; i < pipeInfoModels.size(); ) {
            if (pipeInfoModels.get(i).getDeviceId() != pipeInfoModels.get(i - 1).getDeviceId()) {
                Row dataRow1 = sheet.createRow(j++);
                dataRow1.createCell(0).setCellValue(pipeInfoModels.get(i).getDeviceName());
                dataRow1.createCell(1).setCellValue(pipeInfoModels.get(i).getDeviceTypeName());
                dataRow1.createCell(2).setCellValue(pipeInfoModels.get(i).getDeviceEquipName());
                if (map.get(pipeInfoModels.get(i).getParaName()) != null) {
                    dataRow1.createCell(map.get(pipeInfoModels.get(i).getParaName())).setCellValue(pipeInfoModels.get(i).getParaValue() + pipeInfoModels.get(i).getParaUnit());
                }
                i = writeExcel(dataRow1, i + 1, pipeInfoModels, map);
            } else {
                i = writeExcel(dataRow, i, pipeInfoModels, map);
            }

        }
        try {
            response.setHeader("Content-type", "application/x-download");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode("管道详情.xlsx", "UTF-8"));
            OutputStream out = response.getOutputStream();
            wb.write(out);
        } catch (Exception e) {
            return "导出失败";
        }
        return "下载成功";
    }

    int writeExcel(Row dataRow, int i, List<PipeInfoModel> pipeInfoModels, Map<String, Integer> map) {
        int j = i;
        for (; j < pipeInfoModels.size(); j++) {
            if (pipeInfoModels.get(j).getDeviceId() != pipeInfoModels.get(j - 1).getDeviceId()) {
                return j;
            } else {
                if (map.get(pipeInfoModels.get(j).getParaName()) != null) {
                    dataRow.createCell(map.get(pipeInfoModels.get(j).getParaName())).setCellValue(pipeInfoModels.get(j).getParaValue() + pipeInfoModels.get(j).getParaUnit());
                }
            }
        }
        return j;
    }

    public boolean readExcel(Long unitId, MultipartFile file) {
        com.youngc.pipeline.bean.context.UserBean user
                = (com.youngc.pipeline.bean.context.UserBean) RequestContextHolderUtil.getRequest().getAttribute("user");
        List<PipeInfoModel> pipeInfoModels=new ArrayList<PipeInfoModel>();
        List<DevConfigParaModel> devConfigParaModels=new ArrayList<DevConfigParaModel>();
        FileInputStream input=null;
        try{
            input = (FileInputStream)file.getInputStream();
            Workbook workBook = WorkbookFactory.create(input);
            int numberOfSheets = workBook.getNumberOfSheets();
            for (int s = 0; s < numberOfSheets; s++){
                Sheet sheetAt = workBook.getSheetAt(s);
                int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
                Row headRow=sheetAt.getRow(0);
                int headColumn=headRow.getPhysicalNumberOfCells();
                Map<Integer,String> map=new HashMap<Integer, String>();
                for(int headC=3;headC<headColumn;headC++){
                    map.put(headC,headRow.getCell(headC).getStringCellValue());
                }
                for (int r = 1; r < rowsOfSheet; r++){
                    Row row = sheetAt.getRow(r);
                    if (row == null) {
                        continue;
                    } else{
                        PipeInfoModel pipeInfoModel=new PipeInfoModel();
                        pipeInfoModel.setDeviceName(row.getCell(0)!=null?"'"+row.getCell(0).getStringCellValue()+"'":"");
                        pipeInfoModel.setDeviceTypeName(row.getCell(1)!=null?"'"+row.getCell(1).getStringCellValue()+"'":"''");
                        pipeInfoModel.setDeviceEquipName(row.getCell(2)!=null?"'"+row.getCell(2).getStringCellValue()+"'":"''");
                        String value=pipeInfoModel.getDeviceTypeName();
                        pipeInfoModel.setPressurePipe((value== null||value.equals("''")||value.equals(""))?2:1);
                        pipeInfoModels.add(pipeInfoModel);
                        int column=row.getPhysicalNumberOfCells();
                        for(int c=3;c<column;c++){
                            DevConfigParaModel devConfigParaModel=new DevConfigParaModel();
                            devConfigParaModel.setDeviceName(pipeInfoModel.getDeviceName());
                            devConfigParaModel.setParaName("'"+map.get(c)+"'");
                            if(row.getCell(c).getCellTypeEnum()==CellType.NUMERIC)
                                devConfigParaModel.setParaValue(row.getCell(c)!=null?"'"+row.getCell(c).getStringCellValue()+"'":"");
                            else {
                                devConfigParaModel.setParaValue(row.getCell(c)!=null?"'"+row.getCell(c).getStringCellValue()+"'":"");
                            }
                            devConfigParaModel.setParaType(1);
                            devConfigParaModels.add(devConfigParaModel);
                        }
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
        infoManagerMapper.addDevInfoByExcel(pipeInfoModels,user.getUserId(),unitId);
        infoManagerMapper.addDevConfigParaByExcel(devConfigParaModels,user.getUserId(),unitId);
        return true;
    }
}
