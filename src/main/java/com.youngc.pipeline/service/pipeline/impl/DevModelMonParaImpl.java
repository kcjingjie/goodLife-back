package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevModelMonParaMapper;
import com.youngc.pipeline.model.DevModelMonParaModel;
import com.youngc.pipeline.service.pipeline.DevModelMonParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevModelMonParaImpl implements DevModelMonParaService {
    @Autowired
    private DevModelMonParaMapper devModelMonParaMapper;

    public Page getList(Long modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)devModelMonParaMapper.getList(modelId);
    }

    public DevModelMonParaModel getInfo(Long id) {
        return devModelMonParaMapper.getInfo(id);
    }

    public DevModelMonParaModel updateInfo(DevModelMonParaModel devModelMonParaModel) {
        devModelMonParaMapper.updateInfo(devModelMonParaModel);
        return devModelMonParaModel;
    }

    public DevModelMonParaModel insert(DevModelMonParaModel devModelMonParaModel) {
        devModelMonParaMapper.insert(devModelMonParaModel);
        return devModelMonParaModel;
    }

    public boolean delete(String idList) {
        devModelMonParaMapper.delete(idList);
        return true;
    }

    public DevModelMonParaModel getInfoByCode(Long modelId, String paraId) {
        return devModelMonParaMapper.getInfoByCode(modelId, paraId);
    }
}
