package com.ccw.testonline.entity;


public class Classes {
    private Integer classId;

    private String className;

    private Integer grade;

    private Major major;

    public Classes() {
    }
    
    public Classes(Integer classId2) {
		this.classId=classId2;
	}

	public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}