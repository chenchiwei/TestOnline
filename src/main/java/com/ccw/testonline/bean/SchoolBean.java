package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.entity.School;

public class SchoolBean implements BaseBean<School> {
    private Integer schoolId;

    @NotNull
    private String schoolName;

    private String schoolDesc;

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getSchoolDesc() {
        return schoolDesc;
    }

    public void setSchoolDesc(String schoolDesc) {
        this.schoolDesc = schoolDesc == null ? null : schoolDesc.trim();
    }

	@Override
	public School toAddEntity() {
		School school=new School();
		school.setSchoolName(this.getSchoolName());
		school.setSchoolDesc(this.getSchoolDesc());
		return school;
	}

	@Override
	public Integer getId() {
		return this.schoolId;
	}

	@Override
	public School toEditEntity(School entity) {
		entity.setSchoolName(this.schoolName);
		entity.setSchoolDesc(this.schoolDesc);
		return entity;
	}

	
	

	
}