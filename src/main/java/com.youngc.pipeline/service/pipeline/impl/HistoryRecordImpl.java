package com.youngc.pipeline.service.pipeline.impl;
import com.youngc.pipeline.mapper.pipeline.HistoryRecordMapper;
import com.youngc.pipeline.model.HistoryRecordModel;
import com.youngc.pipeline.service.pipeline.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyan
 */
@Service
public class HistoryRecordImpl implements HistoryRecordService{

    @Autowired
    private HistoryRecordMapper historyRecordMapper;


    public List<HistoryRecordModel> getRecordList() {
        return historyRecordMapper.getList();
    }

    public List<HistoryRecordModel> getRecordListBykeyWord(String id) {
        return null;
    }
}
