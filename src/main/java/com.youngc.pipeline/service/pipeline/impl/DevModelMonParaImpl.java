package com.youngc.pipeline.service.pipeline.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.pipeline.DevModelMonParaMapper;
import com.youngc.pipeline.model.DevModelMonParaModel;
import com.youngc.pipeline.service.pipeline.DevModelMonParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevModelMonParaImpl implements DevModelMonParaService {
    @Autowired
    private DevModelMonParaMapper devModelMonParaMapper;

    /**
     * 分页获取模型监测参数信息
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)devModelMonParaMapper.getList(modelId);
    }

    /**
     * 根据id获取模型监测参数信息
     * @param id
     * @return
     */
    public DevModelMonParaModel getInfo(Long id) {
        return devModelMonParaMapper.getInfo(id);
    }

    /**
     * 修改模型监测参数信息
     * @param devModelMonParaModel
     * @return
     */
    public DevModelMonParaModel updateInfo(DevModelMonParaModel devModelMonParaModel) {
        devModelMonParaMapper.updateInfo(devModelMonParaModel);
        return devModelMonParaModel;
    }

    /**
     * 添加模型监测参数信息
     * @param devModelMonParaModel
     * @return
     */
    public DevModelMonParaModel insert(DevModelMonParaModel devModelMonParaModel) {
        devModelMonParaMapper.insert(devModelMonParaModel);
        return devModelMonParaModel;
    }

    /**
     * 删除模型监测参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devModelMonParaMapper.delete(idList);
        return true;
    }

    /**
     * 查询模型监测参数的标识是否唯一
     */
    public List<DevModelMonParaModel> getInfoByCode(Long modelId, String paraId) {
        return devModelMonParaMapper.getInfoByCode(modelId, paraId);
    }
}
