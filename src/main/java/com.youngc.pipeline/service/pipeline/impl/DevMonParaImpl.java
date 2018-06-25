package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevMonParaMapper;
import com.youngc.pipeline.model.DevMonParaModel;
import com.youngc.pipeline.service.pipeline.DevMonParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevMonParaImpl implements DevMonParaService{
    @Autowired
    private DevMonParaMapper devMonParaMapper;

    //查询设备监测参数信息
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page)devMonParaMapper.getList(deviceId);
    }

    //根据id查看设备监测参数信息
    public DevMonParaModel getInfo(Long id) {
        return devMonParaMapper.getInfo(id);
    }

    //跟新设备监测信息
    public DevMonParaModel updateInfo(DevMonParaModel devMonParaModel) {
        devMonParaMapper.updateInfo(devMonParaModel);
        return devMonParaModel;
    }

    //添加设备监测信息
    public DevMonParaModel insert(DevMonParaModel devMonParaModel) {
        devMonParaMapper.insert(devMonParaModel);
        return devMonParaModel;
    }

    //删除设备监测信息
    public boolean delete(String idList) {
        devMonParaMapper.delete(idList);
        return true;
    }

    //查询设备监测参数标识是否重复
    public DevMonParaModel getInfoByCode(Long deviceId, String paraId) {

        return devMonParaMapper.getInfoByCode(deviceId,paraId);
    }
}
