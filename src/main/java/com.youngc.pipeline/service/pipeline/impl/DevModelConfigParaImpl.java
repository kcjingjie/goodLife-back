package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevModelConfigParaMapper;
import com.youngc.pipeline.model.DevModelConfigParaModel;
import com.youngc.pipeline.service.pipeline.DevModelConfigParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevModelConfigParaImpl implements DevModelConfigParaService{
    @Autowired
    private DevModelConfigParaMapper devModelConfigParaMapper;

    public Page getList(Long modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page)devModelConfigParaMapper.getList(modelId);
    }

    public DevModelConfigParaModel getInfo(Long id) {
        return devModelConfigParaMapper.getInfo(id);
    }

    public DevModelConfigParaModel updateInfo(DevModelConfigParaModel devModelConfigParaModel) {
        devModelConfigParaMapper.updateInfo(devModelConfigParaModel);
        return devModelConfigParaModel;
    }

    public DevModelConfigParaModel insert(DevModelConfigParaModel devModelConfigParaModel) {
        devModelConfigParaMapper.insert(devModelConfigParaModel);
        return devModelConfigParaModel;
    }

    public boolean delete(String idList) {
        devModelConfigParaMapper.delete(idList);
        return true;
    }

    public DevModelConfigParaModel getInfoByCode(Long modelId, String paraId) {
        return devModelConfigParaMapper.getInfoByCode(modelId,paraId);
    }
}

