package com.sweethut.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sweethut.service.entity.PropertyRecord;
import com.sweethut.service.entity.PropertyRecordInfo;
import com.sweethut.service.mapper.PropertyRecordMapper;
import com.sweethut.service.service.PropertyRecordService;
import com.sweethut.service.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyRecordServiceImpl extends ServiceImpl<PropertyRecordMapper, PropertyRecord> implements PropertyRecordService {
    @Resource
    private PropertyRecordMapper propertyRecordMapper;

    @Override
    public List<PropertyRecordInfo> getAllPropertyRecordByYear(String year) {
        QueryWrapper<PropertyRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("date");

        List<PropertyRecord> propertyRecordList = propertyRecordMapper.selectList(wrapper);
        List<String> yearFullMonth = DateUtil.getYearFullMonth(year);

        List<PropertyRecordInfo> propertyRecordInfoList = new ArrayList<>();

        // 遍历指定年的月份
        for (int i = 0; i < yearFullMonth.size(); i++) {
            PropertyRecordInfo propertyRecordInfo = new PropertyRecordInfo();
            propertyRecordInfo.setId(i);
            propertyRecordInfo.setDate(yearFullMonth.get(i));
            propertyRecordInfo.setRole("");
            propertyRecordInfo.setType("");
            propertyRecordInfo.setRemark("");

            List<PropertyRecord> newPropertyRecordList = new ArrayList<>();
            int amount = 0;

            // 遍历资产记录 如果月份和指定月份相等 则添加至children属性列表中
            for (PropertyRecord propertyRecord : propertyRecordList) {
                if (DateUtil.isSameMonth(propertyRecord.getDate(), yearFullMonth.get(i))) {
                    // 统计每月的收支
                    if (propertyRecord.getType().equals("收入")) {
                        amount += propertyRecord.getAmount();
                    } else {
                        amount -= propertyRecord.getAmount();
                    }

                    // 设置children属性列表中的id
                    propertyRecord.setId(propertyRecord.getId() + 100);
                    newPropertyRecordList.add(propertyRecord);
                }
            }

            propertyRecordInfo.setAmount(amount);
            // 设置每月的children属性
            propertyRecordInfo.setChildren(newPropertyRecordList);

            propertyRecordInfoList.add(propertyRecordInfo);
        }

        return propertyRecordInfoList;
    }
}
