package com.sweethut.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweethut.service.entity.TravelRecord;
import com.sweethut.service.mapper.TravelRecordMapper;
import com.sweethut.service.result.AxiosResult;
import com.sweethut.service.service.TravelRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @Author: Cxx
 * @Date: 2023/7/24 20:42
 * @Description: 旅行记录controller
 */
@RestController
@RequestMapping("/travelRecord")
public class TravelRecordController {
    @Resource
    private TravelRecordService travelRecordService;

    @Resource
    private TravelRecordMapper travelRecordMapper;

    @ApiOperation("查询所有的旅游记录")
    @GetMapping("/getAllTravelRecord")
    public AxiosResult getAllTravelRecord() {
        List<TravelRecord> travelRecordList = travelRecordMapper.selectList(null);

        return AxiosResult.ok().data("travelRecordList", travelRecordList);
    }

    @ApiOperation("分页查询所有的旅游记录")
    @GetMapping("/getAllTravelRecordByPage")
    public AxiosResult getAllTravelRecordByPage(@RequestParam Integer currentPage,
                                                @RequestParam Integer pageSize) {
        QueryWrapper<TravelRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("date");
        Page<TravelRecord> travelRecordPage = new Page<>(currentPage, pageSize);
        IPage<TravelRecord> travelRecordList = travelRecordMapper.selectPage(travelRecordPage, wrapper);

        return AxiosResult.ok().data("travelRecordList", travelRecordList);
    }

    @ApiOperation("上传旅游记录图片")
    @PostMapping("/addTravelRecordImage")
    public AxiosResult addTravelRecordImage(@RequestPart("imageList") MultipartFile[] imageList,
                                            @RequestParam String date,
                                            @RequestParam String place) {
        for (MultipartFile image: imageList) {
            File dest = new File("/home/cxx/Documents/sweetHut/travelJournal/" +
                    date + File.separator + place + File.separator + image.getOriginalFilename());
            if (!dest.getParentFile().exists()) {
                boolean mkdirs = dest.getParentFile().mkdirs();
            }

            try {
                // 保存文件
                image.transferTo(dest);
            } catch (Exception exception) {
                return AxiosResult.error().message(exception.getMessage());
            }
        }

        return AxiosResult.ok().message("新增成功");
    }

    @ApiOperation("增加旅游记录")
    @PostMapping("/addTravelRecord")
    public AxiosResult addTravelRecord(@RequestBody TravelRecord travelRecord) {
        boolean isSave = travelRecordService.save(travelRecord);

        if (isSave) {
            return AxiosResult.ok().message("新增成功");
        }else {
            return AxiosResult.error().message("新增失败");
        }
    }
}
