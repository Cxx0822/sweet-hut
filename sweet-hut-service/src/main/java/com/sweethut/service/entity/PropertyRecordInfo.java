package com.sweethut.service.entity;

import lombok.Data;

import java.util.List;

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
