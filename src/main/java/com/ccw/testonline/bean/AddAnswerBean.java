package com.ccw.testonline.bean;

import java.util.List;

public class AddAnswerBean {
	private List<ResultBean> radioResult;
	private List<ResultBean> checkboxResult;
	private List<ResultBean> textResult;
	public List<ResultBean> getRadioResult() {
		return radioResult;
	}
	public void setRadioResult(List<ResultBean> radioResult) {
		this.radioResult = radioResult;
	}
	public List<ResultBean> getCheckboxResult() {
		return checkboxResult;
	}
	public void setCheckboxResult(List<ResultBean> checkboxResult) {
		this.checkboxResult = checkboxResult;
	}
	public List<ResultBean> getTextResult() {
		return textResult;
	}
	public void setTextResult(List<ResultBean> textResult) {
		this.textResult = textResult;
	}
	
}
