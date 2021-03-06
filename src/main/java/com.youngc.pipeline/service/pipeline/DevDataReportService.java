package com.youngc.pipeline.service.pipeline;

import com.youngc.pipeline.model.DevDataReportModel;

import java.util.List;

public interface DevDataReportService {
    List<DevDataReportModel> getDevCountByModel();

    List<DevDataReportModel> getDevConutByType();

    List<DevDataReportModel> getDevCountByUnit();

    List<DevDataReportModel>  getLeakRatio();

    List<DevDataReportModel> getQualification();
}
