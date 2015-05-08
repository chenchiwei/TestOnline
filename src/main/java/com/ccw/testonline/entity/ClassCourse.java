package com.ccw.testonline.entity;

public class ClassCourse {
    private Integer id;

    private Classes clazz;

    private Course course;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Classes getClazz() {
		return clazz;
	}

	public void setClazz(Classes clazz) {
		this.clazz = clazz;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

   
}