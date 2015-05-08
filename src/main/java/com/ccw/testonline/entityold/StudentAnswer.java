package com.ccw.testonline.entityold;

public class StudentAnswer {
    private Integer stuAnswerId;

    private String stuAnswerContent;

    private Integer questionId;

    private Integer stuHwId;

    public Integer getStuAnswerId() {
        return stuAnswerId;
    }

    public void setStuAnswerId(Integer stuAnswerId) {
        this.stuAnswerId = stuAnswerId;
    }

    public String getStuAnswerContent() {
        return stuAnswerContent;
    }

    public void setStuAnswerContent(String stuAnswerContent) {
        this.stuAnswerContent = stuAnswerContent == null ? null : stuAnswerContent.trim();
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getStuHwId() {
        return stuHwId;
    }

    public void setStuHwId(Integer stuHwId) {
        this.stuHwId = stuHwId;
    }
}