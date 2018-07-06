package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevConfigParaMapper;
import com.youngc.pipeline.model.DevConfigParaModel;
import com.youngc.pipeline.service.pipeline.DevConfigParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevConfigParaImpl implements DevConfigParaService{

    @Autowired
    private DevConfigParaMapper devConfigParaMapper;

    /**
     * 分页获取设备标准参数信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page p =  (Page)devConfigParaMapper.getList(deviceId);
        return p;
    }

    /**
     * 查询所有设备标准参数信息
     * @param deviceId
     * @return
     */
    public List getListAll(Long deviceId) {
        return devConfigParaMapper.getList(deviceId);
    }

    /**
     * 根据id查询设备标准参数信息
     * @param id
     * @return
     */
    public DevConfigParaModel getInfo(Long id) {
        return devConfigParaMapper.getInfo(id);
    }

    /**
     * 修改设备标准参数信息
     * @param devConfigParaBean
     * @return
     */
    public DevConfigParaModel updateInfo(DevConfigParaModel devConfigParaModel) {
        devConfigParaMapper.updateInfo(devConfigParaModel);
        return devConfigParaModel;
    }

    /**
     * 添加设备标准参数信息
     * @param devConfigParaBean
     * @return
     */
    public DevConfigParaModel insert(DevConfigParaModel devConfigParaModel) {
        devConfigParaMapper.insert(devConfigParaModel);
        return devConfigParaModel;
    }

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devConfigParaMapper.delete(idList);
        return true;
    }

    /**
     * 查询设备标准参数的标识是否唯一
     * @param deviceId
     * @param paraId
     * @return
     */
    public List<DevConfigParaModel> getInfoByCode(Long deviceId, String paraId) {
        return devConfigParaMapper.getInfoByCode(deviceId,paraId);
    }
}
