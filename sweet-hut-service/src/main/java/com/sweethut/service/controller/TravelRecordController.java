package com.sweethut.service.controller;

import com.sweethut.service.entity.TravelRecord;
import com.sweethut.service.result.R;
import com.sweethut.service.service.TravelRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/travelRecord")
public class TravelRecordController {
    @Resource
    private TravelRecordService travelRecordService;

    @ApiOperation("增加旅游记录")
    @PostMapping("/addTravelRecord")
    public R addTravelRecord(@RequestBody TravelRecord travelRecord) {
        boolean isSave = travelRecordService.save(travelRecord);

        if (isSave) {
            return R.ok().message("注册成功");
        } else {
            return R.error().message("注册失败");
        }
    }
}
