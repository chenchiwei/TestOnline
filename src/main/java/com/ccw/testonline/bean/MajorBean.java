package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Academy;
import com.ccw.testonline.entity.Major;
import com.ccw.testonline.exception.ValidateException;

public class MajorBean implements BaseBean<Major> {

	private Integer majorId;
	
	@NotNull
    private String majorName;

    private String majorDesc;
    
    @NotNull
    private Integer academyId;
	
	public Integer getMajorId() {
		return majorId;
	}

	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getMajorDesc() {
		return majorDesc;
	}

	public void setMajorDesc(String majorDesc) {
		this.majorDesc = majorDesc;
	}

	public Integer getAcademyId() {
		return academyId;
	}

	public void setAcademyId(Integer academyId) {
		this.academyId = academyId;
	}

	@Override
	public Major toAddEntity() throws ValidateException {
		Major major=null;
		try {
			BeanValidation.validate(this);
			major=new Major();
			major.setMajorName(this.majorName);
			major.setMajorDesc(this.majorDesc);
			Academy academy=null;
			if(this.academyId!=null){
				academy=new Academy();
				academy.setAcademyId(this.academyId);
			}
			major.setAcademy(academy);
		} catch (ValidateException e) {
			throw e;
		}
		return major;
	}

	@Override
	public Major toEditEntity(Major major) throws ValidateException {
		try {
			BeanValidation.validate(this);
			major.setMajorName(this.majorName);
			major.setMajorDesc(this.majorDesc);
			Academy academy=null;
			if(this.academyId!=null){
				academy=new Academy();
				academy.setAcademyId(this.academyId);
			}
			major.setAcademy(academy);
		} catch (ValidateException e) {
			throw e;
		}
		return major;
	}

	@Override
	public Integer getId() {
		return this.majorId;
	}

}
