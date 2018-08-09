package com.youngc.pipeline.service.pipeline;

import com.youngc.pipeline.model.HistoryRecordModel;

import java.util.List;

/**
 * @author liuyan
 */
public interface HistoryRecordService {

    List<HistoryRecordModel> getRecordList();

    List<HistoryRecordModel> getRecordListBykeyWord(String id);


}
