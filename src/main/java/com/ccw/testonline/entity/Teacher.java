package com.ccw.testonline.entity;

public class Teacher {
    private Integer teaId;

    private Academy academy;

    private String teaName;

    private Integer teaSex;

    private String teaPassword;

    private String teaEmail;

    private String teaPhone;

    public Integer getTeaId() {
        return teaId;
    }

    public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teacher(Integer teaId) {
		super();
		this.teaId = teaId;
	}

	public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName == null ? null : teaName.trim();
    }

    public Integer getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(Integer teaSex) {
        this.teaSex = teaSex ;
    }

    public String getTeaPassword() {
        return teaPassword;
    }

    public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public void setTeaPassword(String teaPassword) {
        this.teaPassword = teaPassword == null ? null : teaPassword.trim();
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail == null ? null : teaEmail.trim();
    }

    public String getTeaPhone() {
        return teaPhone;
    }

    public void setTeaPhone(String teaPhone) {
        this.teaPhone = teaPhone == null ? null : teaPhone.trim();
    }
}