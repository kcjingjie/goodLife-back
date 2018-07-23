package com.youngc.pipeline.service.pipeline.impl;

import com.youngc.pipeline.mapper.pipeline.DevDataReportMapper;
import com.youngc.pipeline.model.DevDataReportModel;
import com.youngc.pipeline.service.pipeline.DevDataReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevDataReportImpl implements DevDataReportService{

    @Autowired
    private DevDataReportMapper devDataReportMapper;

    /**
     * 根据模型划分查询设备个数
     * @return
     */
    public List<DevDataReportModel> getDevCountByModel() {
        return devDataReportMapper.getDevCountByModel();
    }

    /**
     * 根据管道的类型查询设备个数
     * @return
     */
    public List<DevDataReportModel> getDevConutByType() {

        return devDataReportMapper.getDevCountByType();
    }

    /**
     * 根据单位查询
     */
    public List<DevDataReportModel> getDevCountByUnit() {
        return devDataReportMapper.getDevCountByUnit();
    }


    /**
     * 查询检修率
     * @return
     */
    public List<DevDataReportModel> getLeakRatio(){
        List<DevDataReportModel> devDataReportModels=devDataReportMapper.getLeakRatio();
        return devDataReportModels;
    }

    /**
     * 查询合格率
     * @return
     */
    public List<DevDataReportModel> getQualification(){
        List<DevDataReportModel> devDataReportModels=devDataReportMapper.getQualification();
        return devDataReportModels;
    }
}
