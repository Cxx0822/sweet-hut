package com.sweethut.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sweethut.service.entity.TravelRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TravelRecordMapper extends BaseMapper<TravelRecord> {
}
