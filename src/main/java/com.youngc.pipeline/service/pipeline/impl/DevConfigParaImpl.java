package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevConfigParaMapper;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.service.pipeline.DevConfigParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevConfigParaImpl implements DevConfigParaService{

    @Autowired
    private DevConfigParaMapper devConfigParaMapper;

    //检索设备标准参数列表
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page)devConfigParaMapper.getList(deviceId);
    }

    //根据id查询设备标准参数信息
    public DevConfigParaModel getInfo(Long id) {
        return devConfigParaMapper.getInfo(id);
    }

    //更新设备标准参数信息
    public DevConfigParaModel updateInfo(DevConfigParaModel devConfigParaModel) {
        devConfigParaMapper.updateInfo(devConfigParaModel);
        return devConfigParaModel;
    }

    //添加设备标准参数信息
    public DevConfigParaModel insert(DevConfigParaModel devConfigParaModel) {
        devConfigParaMapper.insert(devConfigParaModel);
        return devConfigParaModel;
    }

    //删除设备标准参数信息
    public boolean delete(String idList) {
        devConfigParaMapper.delete(idList);
        return true;
    }

    //查询参数标识是否重复
    public DevConfigParaModel getInfoByCode(Long deviceId, String paraId) {
        return devConfigParaMapper.getInfoByCode(deviceId,paraId);
    }
}
