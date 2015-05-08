package com.ccw.testonline.entity;

public class HomeWorkQuestion {
    private Integer hwQuestionId;

    private Homework homework;

    private Question question;

    private float score;
    
    public HomeWorkQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HomeWorkQuestion(Homework homework, Question question) {
		super();
		this.homework = homework;
		this.question = question;
	}

	public Integer getHwQuestionId() {
        return hwQuestionId;
    }

    public void setHwQuestionId(Integer hwQuestionId) {
        this.hwQuestionId = hwQuestionId;
    }

	public Homework getHomework() {
		return homework;
	}

	public void setHomework(Homework homework) {
		this.homework = homework;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

}