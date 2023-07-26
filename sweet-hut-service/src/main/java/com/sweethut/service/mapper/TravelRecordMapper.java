package com.sweethut.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sweethut.service.entity.TravelRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 旅行记录Mapper
 */
@Repository
@Mapper
public interface TravelRecordMapper extends BaseMapper<TravelRecord> {
}
