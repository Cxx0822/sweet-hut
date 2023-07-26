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
 * @Description: 旅行记录实体类
 */
@Data
@TableName("travel_record")
@ApiModel(value = "travel_record对象", description = "")
public class TravelRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("area")
    private String area;

    @TableField("person")
    private String person;

    @TableField("place")
    private String place;

    @TableField("content")
    private String content;

    @TableField("image")
    private String image;

    @TableField("date")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;
}
