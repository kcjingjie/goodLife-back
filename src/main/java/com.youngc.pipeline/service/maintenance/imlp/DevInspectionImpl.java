package com.youngc.pipeline.service.maintenance.imlp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.maintenance.DevInspectionMapper;
import com.youngc.pipeline.model.DevInspectionModel;
import com.youngc.pipeline.service.maintenance.DevInspectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevInspectionImpl implements DevInspectionService {

    @Autowired
    private DevInspectionMapper devInspectionMapper;
    //模糊检索巡检信息
    public Page getList(String keyWord, String devName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)devInspectionMapper.getList(keyWord,devName);
    }

    //根据id查询巡检信息
    public DevInspectionModel getInfo(Long id) {
        return devInspectionMapper.getInfo(id);
    }

    //修改巡检计划信息
    public DevInspectionModel updateInfo(DevInspectionModel devInspectionModel) {
        devInspectionMapper.updateInfo(devInspectionModel);
        return devInspectionModel;
    }

    //添加巡检计划
    public DevInspectionModel insert(DevInspectionModel devInspectionModel) {
        devInspectionMapper.insert(devInspectionModel);
        return devInspectionModel;
    }

    //删除巡检计划
    public boolean delete(String idList) {
        devInspectionMapper.delete(idList);
        return true;
    }

    //查询所有设备
    public List getDevList() {
        return devInspectionMapper.getDevList();
    }
}
