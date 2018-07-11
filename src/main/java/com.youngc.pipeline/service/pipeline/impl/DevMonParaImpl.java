package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevMonParaMapper;
import com.youngc.pipeline.model.DevMonParaModel;
import com.youngc.pipeline.service.pipeline.DevMonParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevMonParaImpl implements DevMonParaService{
    @Autowired
    private DevMonParaMapper devMonParaMapper;

    /**
     * 分页获取设备监测参数信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page)devMonParaMapper.getList(deviceId);
    }

    /**
     * 查询设备所有监测参数信息
     * @param deviceId
     * @return
     */
    public List getListAll(Long deviceId) {
        return devMonParaMapper.getList(deviceId);
    }


    /**
     * 根据id获取设备监测参数信息
     * @param id
     * @return
     */
    public DevMonParaModel getInfo(Long id) {
        return devMonParaMapper.getInfo(id);
    }

    /**
     * 修改设备监测参数信息
     * @param devMonParaModel
     * @return
     */
    public DevMonParaModel updateInfo(DevMonParaModel devMonParaModel) {
        devMonParaMapper.updateInfo(devMonParaModel);
        return devMonParaModel;
    }

    /**
     * 添加设备监测参数信息
     * @param devMonParaModel
     * @return
     */
    public DevMonParaModel insert(DevMonParaModel devMonParaModel) {
        devMonParaMapper.insert(devMonParaModel);
        return devMonParaModel;
    }

    /**
     * 删除设备监测参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devMonParaMapper.delete(idList);
        return true;
    }

    /**
     * 查询设备监测参数的标识是否唯一
     * @param deviceId
     * @param paraId
     * @return
     */
    public List<DevMonParaModel> getInfoByCode(Long deviceId, String paraId) {

        return devMonParaMapper.getInfoByCode(deviceId,paraId);
    }
}
