package com.sweethut.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sweethut.service.entity.TravelRecord;
import com.sweethut.service.mapper.TravelRecordMapper;
import org.springframework.stereotype.Service;
import com.sweethut.service.service.TravelRecordService;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 旅行记录Service实现类
 */
@Service
public class TravelRecordServiceImpl extends ServiceImpl<TravelRecordMapper, TravelRecord> implements TravelRecordService {
}
