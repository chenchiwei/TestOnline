package com.ccw.testonline.vo;

public class ClassStatisticsVo {
	private Integer classId;
	private String className;
	private Float minScore;
	private Float maxScore;
	private Float avgScore;
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
		this.className = className;
	}
	public Float getMinScore() {
		return minScore;
	}
	public void setMinScore(Float minScore) {
		this.minScore = minScore;
	}
	public Float getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(Float maxScore) {
		this.maxScore = maxScore;
	}
	public Float getAvgScore() {
		return avgScore;
	}
	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}
	
}
