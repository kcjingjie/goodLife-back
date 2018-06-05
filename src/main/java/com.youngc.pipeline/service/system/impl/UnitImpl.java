package com.youngc.pipeline.service.system.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youngc.pipeline.mapper.system.UnitMapper;
import com.youngc.pipeline.model.UnitModel;
import com.youngc.pipeline.service.system.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitImpl implements UnitService{

    @Autowired
    private UnitMapper unitMapper;
    /**
     * 批量获取单位信息
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page getList(String keyword, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return (Page) unitMapper.getList(keyword);
    }

    /**
     * 获取单位信息
     * @param unitId
     * @return
     */
    public UnitModel getUserDetails(Long unitId){
        return unitMapper.getUnitInfo(unitId);
    }

    /**
     * 修改单位信息
     * @param unitModel
     * @return
     */
    public UnitModel updateUnitDetails(UnitModel unitModel){
        unitMapper.updateUnitDetails(unitModel);
        return unitModel;
    }

    /**
     * 添加单位信息
     * @param unitModel
     * @return
     */
    public UnitModel addUnit(UnitModel unitModel){
        unitMapper.addUnit(unitModel);
        return unitModel;
    }

    /**
     * 删除单位
     * @param unitIds
     * @return
     */
    public boolean deleteUnitList(String unitIds){
        unitMapper.deleteUnitList(unitIds);
        return true;
    }
}
