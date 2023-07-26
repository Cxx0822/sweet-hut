package com.sweethut.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sweethut.service.entity.PropertyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 资产记录Mapper
 */
@Repository
@Mapper
public interface PropertyRecordMapper extends BaseMapper<PropertyRecord> {
}
