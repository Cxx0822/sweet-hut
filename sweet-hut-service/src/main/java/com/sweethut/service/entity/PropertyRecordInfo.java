package com.sweethut.service.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 资产记录类
 */
@Data
public class PropertyRecordInfo {
    private Integer id;

    private String date;

    private String role;

    private String type;

    private Integer amount;

    private String remark;

    private List<PropertyRecord> children;
}
