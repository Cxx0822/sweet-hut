package com.sweethut.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sweethut.service.entity.TravelRecord;
import com.sweethut.service.mapper.TravelRecordMapper;
import com.sweethut.service.result.R;
import com.sweethut.service.service.TravelRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/travelRecord")
public class TravelRecordController {
    @Resource
    private TravelRecordService travelRecordService;

    @Resource
    private TravelRecordMapper travelRecordMapper;

    @ApiOperation("查询所有的旅游记录")
    @GetMapping("/getAllTravelRecord")
    public R getAllTravelRecord() {
        List<TravelRecord> travelRecordList = travelRecordMapper.selectList(null);

        return R.ok().data("travelRecordList", travelRecordList);
    }

    @ApiOperation("分页查询所有的旅游记录")
    @GetMapping("/getAllTravelRecordByPage")
    public R getAllTravelRecordByPage(@RequestParam Integer currentPage,
                                    @RequestParam Integer pageSize) {
        QueryWrapper<TravelRecord> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("date");
        Page<TravelRecord> travelRecordPage = new Page<>(currentPage, pageSize);
        IPage<TravelRecord> travelRecordIPage = travelRecordMapper.selectPage(travelRecordPage, wrapper);

        return R.ok().data("travelRecordList", travelRecordIPage);
    }

    @ApiOperation("上传旅游记录图片")
    @PostMapping("/addTravelRecordImage")
    public R addTravelRecordImage(@RequestPart("imageList") MultipartFile[] imageList,
                                  @RequestParam String date,
                                  @RequestParam String place) {
        for (MultipartFile image: imageList) {
            File dest = new File("/home/cxx/Documents/sweetHut/travelJournal/" +
                    date + File.separator + place + File.separator + image.getOriginalFilename());
            if (!dest.getParentFile().exists()) {
                boolean mkdirs = dest.getParentFile().mkdirs();
            }

            try {
                image.transferTo(dest); // 保存文件
            } catch (Exception exception) {
                return R.error().message(exception.getMessage());
            }
        }

        return R.ok().message("新增成功");
    }

    @ApiOperation("增加旅游记录")
    @PostMapping("/addTravelRecord")
    public R addTravelRecord(@RequestBody TravelRecord travelRecord) {
        boolean isSave = travelRecordService.save(travelRecord);

        if (isSave) {
            return R.ok().message("新增成功");
        }else {
            return R.error().message("新增失败");
        }
    }
}
