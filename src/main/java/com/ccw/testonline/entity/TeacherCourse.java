package com.ccw.testonline.entity;

public class TeacherCourse {
    private Integer teaCourseId;

    private Teacher teacher;

    private Course course;

    public TeacherCourse(Integer teaCourseId, Teacher teacher, Course course) {
		super();
		this.teaCourseId = teaCourseId;
		this.teacher = teacher;
		this.course = course;
	}

	public Integer getTeaCourseId() {
        return teaCourseId;
    }

    public void setTeaCourseId(Integer teaCourseId) {
        this.teaCourseId = teaCourseId;
    }

	public Teacher getTeacher() {
		return teacher;
	}

	public TeacherCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

   
}