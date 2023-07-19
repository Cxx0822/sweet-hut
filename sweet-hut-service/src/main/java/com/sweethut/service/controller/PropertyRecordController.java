package com.sweethut.service.controller;

import com.sweethut.service.entity.PropertyRecord;
import com.sweethut.service.mapper.PropertyRecordMapper;
import com.sweethut.service.result.R;
import com.sweethut.service.service.PropertyRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Wrapper;

@RestController
@RequestMapping("/propertyRecord")
public class PropertyRecordController {
    @Resource
    private PropertyRecordService propertyRecordService;

    @Resource
    private PropertyRecordMapper propertyRecordMapper;

    @ApiOperation("根据年份查询所有的资产记录")
    @GetMapping("/getAllPropertyRecordByYear")
    public R getAllPropertyRecordByYear(@RequestParam String year) {
        return R.ok().data("propertyRecordInfoList", propertyRecordService.getAllPropertyRecordByYear(year));
    }

    @ApiOperation("增加资产记录")
    @PostMapping("/addPropertyRecord")
    public R addPropertyRecord(@RequestBody PropertyRecord propertyRecord) {
        boolean isSave = propertyRecordService.save(propertyRecord);

        if (isSave) {
            return R.ok().message("新增成功");
        }else {
            return R.error().message("新增失败");
        }
    }

    @ApiOperation("更新资产记录")
    @PostMapping("/updatePropertyRecord")
    public R updatePropertyRecord(@RequestBody PropertyRecord propertyRecord) {
        int update = propertyRecordMapper.updateById(propertyRecord);

        if (update > 0) {
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

    @ApiOperation("删除资产记录")
    @DeleteMapping("/deletePropertyRecord")
    public R deletePropertyRecord(@RequestParam Integer id) {
        int delete = propertyRecordMapper.deleteById(id);

        if (delete > 0) {
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }
}
