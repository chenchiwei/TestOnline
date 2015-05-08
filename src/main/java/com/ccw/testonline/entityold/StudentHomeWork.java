package com.ccw.testonline.entityold;

import java.util.Date;

public class StudentHomeWork {
    private Integer stuHwId;

    private Integer teaHwId;

    private Date stuHwSubmitTime;

    private Integer stuId;

    public Integer getStuHwId() {
        return stuHwId;
    }

    public void setStuHwId(Integer stuHwId) {
        this.stuHwId = stuHwId;
    }

    public Integer getTeaHwId() {
        return teaHwId;
    }

    public void setTeaHwId(Integer teaHwId) {
        this.teaHwId = teaHwId;
    }

    public Date getStuHwSubmitTime() {
        return stuHwSubmitTime;
    }

    public void setStuHwSubmitTime(Date stuHwSubmitTime) {
        this.stuHwSubmitTime = stuHwSubmitTime;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}