package ink.qs.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ink.qs.eduservice.entity.EduTeacher;
import ink.qs.eduservice.entity.TeacherCondQuery;
import ink.qs.eduservice.service.EduTeacherService;
import ink.qs.utils.Re;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author qs
 * @since 2021-02-15
 */
@Api("讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("findAll")
    public Re list(){
        List<EduTeacher> teachers = eduTeacherService.list(null);
        return Re.ok().data("teachers",teachers);
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("{id}")
    public Re removeById(@PathVariable String id){
        if (eduTeacherService.removeById(id)) {
            return Re.ok().message("删除成功");
        }
        return Re.error().message("删除失败");
    }

//    @ApiOperation(value = "分页讲师列表")
//    @GetMapping("{page}/{limit}")
//    public Re pageList(
//            @ApiParam(name = "page", value = "当前页码", required = true)
//            @PathVariable Long page,
//            @ApiParam(name = "limit", value = "每页记录数", required = true)
//            @PathVariable Long limit){
//        Page<EduTeacher> pageParam = new Page<>(page, limit);
//        eduTeacherService.page(pageParam, null);
//        List<EduTeacher> records = pageParam.getRecords();
//        long total = pageParam.getTotal();
//        return Re.ok().data("total", total).data("rows", records);
//    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public Re save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        if (eduTeacherService.save(teacher)) {
            return Re.ok();
        }
        return Re.error().message("添加讲师失败");
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public Re getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Re.ok().data("item", teacher);
    }

    @ApiOperation("根据id修改讲师信息")
    @PutMapping("{id}")
    public Re updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){
        teacher.setId(id);
        if (eduTeacherService.updateById(teacher)) {
            return Re.ok().message("修改讲师信息成功");
        }
        return Re.error().message("修改讲师信息失败");
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public Re CondQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = true)
            TeacherCondQuery teacherCondQuery){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.condQuery(pageParam, teacherCondQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return Re.ok().data("total", total).data("rows", records);
    }
}

