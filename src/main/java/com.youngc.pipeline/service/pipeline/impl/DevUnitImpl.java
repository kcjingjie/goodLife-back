package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevUnitMapper;
import com.youngc.pipeline.model.DevUnitModel;
import com.youngc.pipeline.service.pipeline.DevUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevUnitImpl implements DevUnitService {

    @Autowired
    private DevUnitMapper devUnitMapper;

    /**
     * 分页获取设备管件信息
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize) ;
        return (Page)devUnitMapper.getList(deviceId);
    }

    /**
     * 根据id查询设备管件信息
     * @param id
     * @return
     */
    public DevUnitModel getInfo(Long id) {
        return devUnitMapper.getInfo(id);
    }

    /**
     * 修改设备管件信息
     * @param devUnitModel
     * @return
     */
    public DevUnitModel updateInfo(DevUnitModel devUnitModel) {
        devUnitMapper.updateInfo(devUnitModel);
        return devUnitModel;
    }

    /**
     * 添加设备管件信息
     * @param devUnitModel
     * @return
     */
    public DevUnitModel insert(DevUnitModel devUnitModel) {
        devUnitMapper.insert(devUnitModel);
        return devUnitModel;
    }

    /**
     * 删除设备管件信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devUnitMapper.delete(idList);
        return true;
    }
}
