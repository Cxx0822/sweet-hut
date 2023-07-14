package com.sweethut.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sweethut.service.entity.PropertyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PropertyRecordMapper extends BaseMapper<PropertyRecord> {
}
