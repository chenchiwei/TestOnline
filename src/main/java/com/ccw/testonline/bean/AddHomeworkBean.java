package com.ccw.testonline.bean;

public class AddHomeworkBean {
	private String teaHwName;
	private Integer courseId;
	private Integer[] questionIds;
	private Integer[] classIds;
	private Float[] scores;
	public String getTeaHwName() {
		return teaHwName;
	}
	public void setTeaHwName(String teaHwName) {
		this.teaHwName = teaHwName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer[] getQuestionIds() {
		return questionIds;
	}
	public void setQuestionIds(Integer[] questionIds) {
		this.questionIds = questionIds;
	}
	public Integer[] getClassIds() {
		return classIds;
	}
	public void setClassIds(Integer[] classIds) {
		this.classIds = classIds;
	}
	public Float[] getScores() {
		return scores;
	}
	public void setScores(Float[] scores) {
		this.scores = scores;
	}
	
}
