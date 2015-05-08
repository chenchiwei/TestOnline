package com.ccw.testonline.entity;

public class ClassTeacher {
    private Integer id;

    private Classes clazz;

    private Teacher teacher;


	public ClassTeacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClassTeacher(Classes clazz, Teacher teacher) {
		super();
		this.clazz = clazz;
		this.teacher = teacher;
	}

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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
 
}