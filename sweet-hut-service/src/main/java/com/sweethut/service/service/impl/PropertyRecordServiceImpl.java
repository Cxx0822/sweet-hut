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
        wrapper.orderByDesc("date");

        List<PropertyRecord> propertyRecordList = propertyRecordMapper.selectList(wrapper);
        List<String> yearFullMonth = DateUtil.getYearFullMonth(year);

        List<PropertyRecordInfo> propertyRecordInfoList = new ArrayList<>();

        for (int i = 0; i < yearFullMonth.size(); i++) {
            PropertyRecordInfo propertyRecordInfo = new PropertyRecordInfo();
            propertyRecordInfo.setId(i);
            propertyRecordInfo.setDate(yearFullMonth.get(i));
            propertyRecordInfo.setRole("");
            propertyRecordInfo.setType("");
            propertyRecordInfo.setRemark("");

            List<PropertyRecord> newPropertyRecordList = new ArrayList<>();
            int amount = 0;

            for (PropertyRecord propertyRecord : propertyRecordList) {
                if (DateUtil.isSameMonth(propertyRecord.getDate(), yearFullMonth.get(i))) {
                    if (propertyRecord.getType().equals("收入")) {
                        amount += propertyRecord.getAmount();
                    }else {
                        amount -= propertyRecord.getAmount();
                    }

                    propertyRecord.setId(propertyRecord.getId() + i * 12);
                    newPropertyRecordList.add(propertyRecord);
                }
            }

            propertyRecordInfo.setAmount(amount);
            propertyRecordInfo.setChildren(newPropertyRecordList);

            propertyRecordInfoList.add(propertyRecordInfo);
        }

        return propertyRecordInfoList;
    }
}
