package com.sweethut.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sweethut.service.entity.PropertyRecord;
import com.sweethut.service.entity.PropertyRecordInfo;

import java.util.List;

public interface PropertyRecordService extends IService<PropertyRecord> {
    List<PropertyRecordInfo> getAllPropertyRecordByYear(String year);
}
