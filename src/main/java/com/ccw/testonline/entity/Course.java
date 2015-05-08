package com.ccw.testonline.entity;

public class Course {
    private Integer courseId;

    private String courseName;

    private String courseDesc;

    private TeacherCourse teacherCourse; 
    
    public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Integer courseId2) {
		this.courseId=courseId2;
	}

	public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}

	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}

	public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc == null ? null : courseDesc.trim();
    }
}