package com.ccw.testonline.entity;


public class Academy {
    private Integer academyId;

    private String academyName;

    private String academyDesc;

    private School school;

    public Integer getAcademyId() {
        return academyId;
    }

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public String getAcademyName() {
        return academyName;
    }

    public void setAcademyName(String academyName) {
        this.academyName = academyName == null ? null : academyName.trim();
    }

    public String getAcademyDesc() {
        return academyDesc;
    }

    public void setAcademyDesc(String academyDesc) {
        this.academyDesc = academyDesc == null ? null : academyDesc.trim();
    }

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

}