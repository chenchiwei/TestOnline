package com.ccw.testonline.entity;

import java.util.Date;

public class StudentHomework {
    private Integer id;

    private Student student;

    private Homework homework;
    
    private Date submitTime;
    
    private float score;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public StudentHomework() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentHomework(Student student, Homework homework, Date submitTime) {
		super();
		this.student = student;
		this.homework = homework;
		this.submitTime = submitTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	
}