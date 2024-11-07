package com.nikki.shixunpro.entity;

import java.util.List;

public class Course {
    private int courseId;//课程序号
    private String courseName;//课程名称
    private String author;//课程上传作者
    private double credit;//课程学分
    private double courseHour;//课时
    private String courseType;//课程类型
    private String courseCover;//课程封面
    private String courseDescription;//课程描述
    private boolean state;//课程审核状态
    private String beginTime;//课程开始时间
    private String endTime;//课程结束时间
    private String courseVideo;//课程视频

    public Course() {}

    public Course(int courseId, String courseName, String author, double credit, double courseHour, String courseType,
                  String courseCover, String courseDescription, boolean state, String beginTime, String endTime, String courseVideo) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.author = author;
        this.credit = credit;
        this.courseHour = courseHour;
        this.courseType = courseType;
        this.courseCover = courseCover;
        this.courseDescription = courseDescription;
        this.state = state;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.courseVideo = courseVideo;
    }

    public Course(String courseName, String author, double credit, double courseHour, String courseType, String courseCover, String courseDescription, boolean state, String beginTime, String endTime, String courseVideo) {
        this.courseName = courseName;
        this.author = author;
        this.credit = credit;
        this.courseHour = courseHour;
        this.courseType = courseType;
        this.courseCover = courseCover;
        this.courseDescription = courseDescription;
        this.state = state;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.courseVideo = courseVideo;
    }

    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", author='" + author + '\'' +
                ", credit='" + credit + '\'' +
                ", courseHour='" + courseHour + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseCover='" + courseCover + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", state=" + state +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                '}';
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public double getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(double courseHour) {
        this.courseHour = courseHour;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }
}

