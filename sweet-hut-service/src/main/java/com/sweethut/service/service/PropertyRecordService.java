package com.sweethut.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sweethut.service.entity.PropertyRecord;
import com.sweethut.service.entity.PropertyRecordInfo;

import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 资产记录Service接口
 */
public interface PropertyRecordService extends IService<PropertyRecord> {
    /**
     * 根据年份获取资产记录
     *
     * @param year 年份
     * @return 资产记录
     */
    List<PropertyRecordInfo> getAllPropertyRecordByYear(String year);
}
