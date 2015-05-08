package com.ccw.testonline.entityold;

import java.util.Date;

public class TeacherHomeWork {
    private Integer teaHwId;

    private String teaHwName;

    private Integer classId;

    private Date teaHwPublishTime;

    private Date teaHwSubmitTime;

    private Integer teaId;

    private Integer courseId;

    public Integer getTeaHwId() {
        return teaHwId;
    }

    public void setTeaHwId(Integer teaHwId) {
        this.teaHwId = teaHwId;
    }

    public String getTeaHwName() {
        return teaHwName;
    }

    public void setTeaHwName(String teaHwName) {
        this.teaHwName = teaHwName == null ? null : teaHwName.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Date getTeaHwPublishTime() {
        return teaHwPublishTime;
    }

    public void setTeaHwPublishTime(Date teaHwPublishTime) {
        this.teaHwPublishTime = teaHwPublishTime;
    }

    public Date getTeaHwSubmitTime() {
        return teaHwSubmitTime;
    }

    public void setTeaHwSubmitTime(Date teaHwSubmitTime) {
        this.teaHwSubmitTime = teaHwSubmitTime;
    }

    public Integer getTeaId() {
        return teaId;
    }

    public void setTeaId(Integer teaId) {
        this.teaId = teaId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}