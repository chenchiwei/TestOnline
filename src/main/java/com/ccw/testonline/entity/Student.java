package com.ccw.testonline.entity;


public class Student {
    private Integer stuId;

    private String stuName;

    private String stuPassword;

    private Classes clazz;
    
    private Integer stuSex;

    private String stuEmail;

    private String stuPhone;

    public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(Integer stuId) {
		super();
		this.stuId = stuId;
	}

	public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword == null ? null : stuPassword.trim();
    }

	public Classes getClazz() {
		return clazz;
	}

	public Integer getStuSex() {
		return stuSex;
	}

	public void setStuSex(Integer stuSex2) {
		this.stuSex = stuSex2;
	}

	public String getStuEmail() {
		return stuEmail;
	}

	public void setStuEmail(String stuEmail) {
		this.stuEmail = stuEmail;
	}

	public String getStuPhone() {
		return stuPhone;
	}

	public void setStuPhone(String stuPhone) {
		this.stuPhone = stuPhone;
	}

	public void setClazz(Classes clazz) {
		this.clazz = clazz;
	}

}