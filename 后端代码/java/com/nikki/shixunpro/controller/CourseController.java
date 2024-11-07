package com.nikki.shixunpro.controller;

import com.nikki.shixunpro.entity.Course;
import com.nikki.shixunpro.mapper.CourseMapper;
import com.nikki.shixunpro.biz.CourseBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CourseController {
    @Autowired
    private CourseBiz biz;
    @Autowired
    private CourseMapper courseMapper;

    //查询课程列表
    @RequestMapping("/course/list")
    public Map findCourse(HttpServletRequest request){
        List<Course> list = biz.findAll();
        Map result = new HashMap<>();
        result.put("isOk",true);
        result.put("msg", "查询课程列表成功！");
        result.put("course",list);
        return result;
    }


    //添加课程
    @RequestMapping("/course/add")
    public Map addCourse(@RequestBody Course course){
        this.biz.addCourse(course);
        Map result = new HashMap<>();
        result.put("isOk",true);
        result.put("msg","添加课程成功！");
        return result;
    }

    //删除课程
    @RequestMapping("/course/delete")
    public Map deleteCourse(@RequestParam int courseId){

        Map result = new HashMap<>();
        if(biz.deleteCourse(courseId)){
            result.put("isOk",true);
            result.put("msg","删除课程成功！");
        }else {
            result.put("isOk",false);
            result.put("msg","删除课程失败");
        }
        return result;
    }

    //更新课程
    @RequestMapping("/course/update")
    public Map updateCourse(@RequestBody Course course){
        this.biz.updateCourse(course);
        Map result = new HashMap<>();
        result.put("isOk",true);
        result.put("msg","更新课程成功！");
        return result;
    }

    //更新课程
    @RequestMapping("/course/updateState")
    public Map updateCourseState(@RequestBody Course course){
        this.biz.updateCourseState(course);
        Map result = new HashMap<>();
        result.put("isOk",true);
        result.put("msg","更新课程成功！");
        return result;
    }

    //查询课程
    @RequestMapping("/course/search")
    public Map searchCourse(@RequestParam String courseName){
        Map result = new HashMap<>();
        Course course = biz.selectCourseByName(courseName);

        if(course != null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("course",course);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

    //查询课程
    @RequestMapping("/course/search2")
    public Map searchCourse2(@RequestParam String courseName){
        Map result = new HashMap<>();
        List<Course> course = biz.selectCourseByName2(courseName);

        if(course != null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("course",course);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

    //模糊搜索课程
    @RequestMapping("/course/fuzzySearch")
    public Map fuzzySearchCourse(@RequestParam String courseName){

        Map result = new HashMap<>();

        List<Course> course = biz.fuzzySearchCourseByName(courseName);

        if(course != null){
            result.put("isOk", true);
            result.put("msg", "模糊搜索成功");
            result.put("course", course);
        } else {
            result.put("isOk", false);
            result.put("msg", "未找到匹配课程");
        }

        return result;
    }

    //查询课程状态
    @RequestMapping("/course/searchByState")
    public Map searchCourseState(@RequestParam boolean state){
        Map result = new HashMap<>();
        List<Course> course = biz.selectCourseByState(state);

        if(course != null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("course",course);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }

    //依据id查询课程信息
    @RequestMapping("/course/searchById")
    public Map searchCourseId(@RequestParam int courseId){
        Map result = new HashMap<>();
        Course course = biz.selectCourseById(courseId);
        if(course != null){
            result.put("isOk",true);
            result.put("msg","查询课程成功！");
            result.put("course",course);
        }else {
            result.put("isOk",false);
            result.put("msg","查询课程失败！");
        }
        return result;
    }


}

