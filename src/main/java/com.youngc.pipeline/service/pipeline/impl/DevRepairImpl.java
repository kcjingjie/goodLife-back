package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevRepairMapper;
import com.youngc.pipeline.model.DevRepairModel;
import com.youngc.pipeline.service.pipeline.DevRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevRepairImpl implements DevRepairService {
    @Autowired
    DevRepairMapper devRepairMapper;

    /**
     * 分页获取设备备件
     * @param deviceId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long deviceId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page p =  (Page)devRepairMapper.getList(deviceId);
        return p;
    }

    /**
     * 根据id查询设备备件
     * @param id
     * @return
     */
    public DevRepairModel getInfo(Long id) {
        return devRepairMapper.getInfo(id);
    }

    /**
     * 修改设备标准参数信息
     * @param devRepairModel
     * @return
     */
    public DevRepairModel updateInfo(DevRepairModel devRepairModel) {
        devRepairMapper.updateInfo(devRepairModel);
        return devRepairModel;
    }

    /**
     * 添加设备标准参数信息
     * @param devRepairModel
     * @return
     */
    public DevRepairModel insert(DevRepairModel devRepairModel) {
        devRepairMapper.insert(devRepairModel);
        return devRepairModel;
    }

    /**
     * 删除设备标准参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devRepairMapper.delete(idList);
        return true;
    }

}
