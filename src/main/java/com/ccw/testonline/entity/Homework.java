package com.ccw.testonline.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Homework {
    private Integer teaHwId;

    private String teaHwName;

    private Date teaHwAskTime;

    private List<ClassHomework> classHomework=new ArrayList<>();
    
    private Course course;
    
    private Integer status;
    
    private int flag;//用来返回标明是否是编辑，1为是，0为否
    
    public Homework() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Homework(String teaHwName, Date teaHwAskTime, Course course) {
		super();
		this.teaHwName = teaHwName;
		this.teaHwAskTime = teaHwAskTime;
		this.course = course;
	}

	public Homework(Integer teaHwId) {
		super();
		this.teaHwId = teaHwId;
	}

	public Integer getTeaHwId() {
        return teaHwId;
    }

    public void setTeaHwId(Integer teaHwId) {
        this.teaHwId = teaHwId;
    }

	public List<ClassHomework> getClassHomework() {
		return classHomework;
	}

	public void setClassHomework(List<ClassHomework> classHomework) {
		this.classHomework = classHomework;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTeaHwName() {
        return teaHwName;
    }

    public void setTeaHwName(String teaHwName) {
        this.teaHwName = teaHwName == null ? null : teaHwName.trim();
    }

    public Date getTeaHwAskTime() {
        return teaHwAskTime;
    }

    public void setTeaHwAskTime(Date teaHwAskTime) {
        this.teaHwAskTime = teaHwAskTime;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}