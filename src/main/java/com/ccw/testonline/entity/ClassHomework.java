package com.ccw.testonline.entity;

public class ClassHomework {
    private Integer id;

    private Classes clazz;

    private Homework homework;

    public Homework getHomework() {
		return homework;
	}

	public ClassHomework(Classes clazz, Homework homework) {
		super();
		this.clazz = clazz;
		this.homework = homework;
	}

	public ClassHomework() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
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

}