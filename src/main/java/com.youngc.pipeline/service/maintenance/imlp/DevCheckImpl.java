package com.youngc.pipeline.service.maintenance.imlp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.maintenance.DevCheckMapper;
import com.youngc.pipeline.model.DevCheckModel;
import com.youngc.pipeline.service.maintenance.DevCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevCheckImpl implements DevCheckService{
    @Autowired
    private DevCheckMapper devCheckMapper;

    /**
     * 分页模糊查询检验计划的内容
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList( String devName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)devCheckMapper.getList(devName);
    }

    /**
     * 分页模糊查询检验计划的内容
     * @param devName
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getNeedList( String devName, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page)devCheckMapper.getNeedList(devName);
    }

    /**
     * 通过id获取检验计划的内容
     * @param id
     * @return
     */
    public DevCheckModel getInfo(Long id) {
        return devCheckMapper.getInfo(id);
    }

    /**
     * 修改计划内容
     * @param devCheckModel
     * @return
     */
    public DevCheckModel updateInfo(DevCheckModel devCheckModel) {
        devCheckMapper.updateInfo(devCheckModel);
        return devCheckModel;
    }

    /**
     * 添加计划信息
     * @param devCheckModel
     * @return
     */
    public DevCheckModel insert(DevCheckModel devCheckModel) {
        devCheckMapper.insert(devCheckModel);
        return devCheckModel;
    }

    /**
     * 删除计划信息
     * @param idList
     * @return
     */
    public boolean delete(String idList) {
        devCheckMapper.delete(idList);
        return true;
    }

    /**
     * 提交计划信息
     * @param devCheckModel
     * @return
     */
    public DevCheckModel submitInfo(DevCheckModel devCheckModel) {
        devCheckMapper.submitInfo(devCheckModel);
        return devCheckModel;
    }
}
