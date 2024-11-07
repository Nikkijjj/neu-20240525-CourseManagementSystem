package com.nikki.shixunpro.biz;

import com.nikki.shixunpro.entity.Course;
import com.nikki.shixunpro.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseBiz {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> findAll() {
        return courseMapper.selectCourse();
    }


    public void addCourse(Course course) {
        courseMapper.insertCourse(course);
    }

    public boolean deleteCourse(int courseId){
        return courseMapper.deleteCourseById(courseId)>0;
    }

    public void updateCourse(Course course) {
        this.courseMapper.updateCourse(course);
    }


    public void updateCourseState(Course course) {
        this.courseMapper.updateCourseState(course);
    }

    public Course selectCourseByName(String courseName) {
        Course course = courseMapper.selectCourseByName(courseName);
        return course;
    }

    public List<Course> selectCourseByName2(String courseName) {
        List<Course> course = courseMapper.selectCourseByName2(courseName);
        return course;
    }

    public List<Course> fuzzySearchCourseByName(String courseName) {
        List<Course> course = courseMapper.fuzzySearchCourseByName(courseName);
        return course;
    }

    public Course selectCourseById(int courseId) {
        Course course = courseMapper.selectCourseById(courseId);
        return course;
    }

    public List<Course> selectCourseByState(boolean state) {
        List<Course> course = courseMapper.selectCourseByState(state);
        return course;
    }
}

