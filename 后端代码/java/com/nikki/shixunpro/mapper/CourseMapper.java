package com.nikki.shixunpro.mapper;

import com.nikki.shixunpro.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select * from t_course")
    List<Course> selectCourse();

    @Select("select * from t_course where courseName like concat('%', #{courseName}, '%')")
    List<Course> fuzzySearchCourseByName(@Param("courseName") String courseName);

    @Select("select courseId, courseName, courseDescription from t_course")
    List<Course> selectCourse_2();

    @Select("select * from t_course where courseName=#{courseName}")
    Course selectCourseByName(@Param("courseName") String courseName);

    @Select("select * from t_course where courseName=#{courseName}")
    List<Course> selectCourseByName2(@Param("courseName") String courseName);

    @Select("select * from t_course where courseId=#{courseId}")
    Course selectCourseById(@Param("courseId") int courseId);

    @Select("select * from t_course where state=#{state}")
    List<Course> selectCourseByState(@Param("state") boolean state);

    @Insert("insert into t_course values (null,#{courseName}, #{author}, #{credit}, #{courseHour}, #{courseType}, #{courseCover}, #{courseDescription}, #{state}, #{beginTime}, #{endTime}, #{courseVideo})")
    int insertCourse(Course course);

    @Delete("delete from t_course where courseId=#{courseId}")
    int deleteCourseById(int courseId);

    @Update("update t_course set courseName=#{courseName}, author=#{author}, credit=#{credit}, courseHour=#{courseHour}, courseType=#{courseType}, courseCover=#{courseCover}, courseDescription=#{courseDescription}, state=#{state}, beginTime=#{beginTime},endTime=#{endTime}, courseVideo=#{courseVideo} where courseId=#{courseId}")
    int updateCourse(Course course);

    @Update("update t_course set state=#{state} where courseId=#{courseId}")
    int updateCourseState(Course course);


}
