package com.youngc.pipeline.service.maintenance.imlp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.maintenance.DevLeakMapper;
import com.youngc.pipeline.model.DevDataReportModel;
import com.youngc.pipeline.model.DevLeakModel;
import com.youngc.pipeline.service.maintenance.DevLeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevLeakImpl implements DevLeakService {

    @Autowired
    private DevLeakMapper devLeakMapper;

    /**
     * 分页检索泄漏点信息
     * @param keyWord
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyWord, String devName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) devLeakMapper.getList(keyWord,devName);
    }

    /**
     * 分页检索未处理泄漏点信息
     * @param keyWord
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getUnhandle(String keyWord, String devName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) devLeakMapper.getUnhandle(keyWord,devName);
    }

    /**
     * 根据id查询泄漏点信息
     * @param id
     * @return
     */
    public DevLeakModel getInfo(Long id) {
        return devLeakMapper.getInfo(id);
    }

    /**
     * 修改泄漏点信息
     * @param devLeakModel
     * @return
     */
    public DevLeakModel updateInfo(DevLeakModel devLeakModel) {
        devLeakMapper.updateInfo(devLeakModel);
        return devLeakModel;
    }

    /**
     * 添加泄漏点信息
     * @param devLeakModel
     * @return
     */
    public DevLeakModel insert(DevLeakModel devLeakModel) {
        devLeakMapper.insert(devLeakModel);
        return devLeakModel;
    }

    /**
     * 删除泄漏点信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devLeakMapper.delete(idList);
        return true;
    }

    /**
     * 查询所有设备
     * @return
     */
    public List getDevList() {
        return devLeakMapper.getDevList();
    }

    /**
     * 查询编号是否唯一
     * @param leakNo
     * @return
     */
    public List<DevLeakModel> getInfoByNo(String leakNo) {
        return devLeakMapper.getInfoByNo(leakNo);
    }

    /**
     * 修改状态
     * @param id
     * @return
     */
    public boolean changeStatus(Long id) {
        return devLeakMapper.changeStatus(id);
    }


}
