package com.sweethut.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 资产记录实体类
 */
@Data
@TableName("property_record")
@ApiModel(value = "property_record对象", description = "")
public class PropertyRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

    @TableField("role")
    private String role;

    @TableField("type")
    private String type;

    @TableField("amount")
    private Integer amount;

    @TableField("remark")
    private String remark;
}
