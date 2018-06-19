package com.youngc.pipeline.service.system;

import com.github.pagehelper.Page;
import com.youngc.pipeline.model.UnitModel;

public interface UnitService {

    Page getList(String keyword, int pageNum, int pageSize);

    UnitModel getUnitDetails(Long unitId);

    UnitModel updateUnitDetails(UnitModel unitModel);

    UnitModel addUnit(UnitModel unitModel);

    boolean deleteUnitList(String unitIds);

    UnitModel getUnitByCode(String code);
}
