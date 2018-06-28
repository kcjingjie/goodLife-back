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

    /**
     * 分页获取模型标准参数信息
     * @param modelId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(Long modelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return (Page)devModelConfigParaMapper.getList(modelId);
    }

    /**
     * 根据id获取模型标准参数信息
     * @param id
     * @return
     */
    public DevModelConfigParaModel getInfo(Long id) {
        return devModelConfigParaMapper.getInfo(id);
    }

    /**
     * 修改模型标准参数信息
     * @param devModelConfigParaBean
     * @return
     */
    public DevModelConfigParaModel updateInfo(DevModelConfigParaModel devModelConfigParaModel) {
        devModelConfigParaMapper.updateInfo(devModelConfigParaModel);
        return devModelConfigParaModel;
    }

    /**
     * 添加模型标准参数信息
     * @param devModelConfigParaBean
     * @return
     */
    public DevModelConfigParaModel insert(DevModelConfigParaModel devModelConfigParaModel) {
        devModelConfigParaMapper.insert(devModelConfigParaModel);
        return devModelConfigParaModel;
    }

    /**
     * 删除模型标准参数信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devModelConfigParaMapper.delete(idList);
        return true;
    }

    /**
     * 查询模型标准参数标识是否唯一
     * @param modelId
     * @param paraId
     * @return
     */
    public DevModelConfigParaModel getInfoByCode(Long modelId, String paraId) {
        return devModelConfigParaMapper.getInfoByCode(modelId,paraId);
    }
}

