package com.ccw.testonline.entity;

import com.ccw.testonline.entity.Question;

public class Answer {
    private Integer answerId;

    private StudentHomework studentHomework;
    
    private String answerContent;

    private Question question;

    public Integer getAnswerId() {
        return answerId;
    }

    public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(StudentHomework studentHomework, String answerContent,
			Question question) {
		super();
		this.studentHomework = studentHomework;
		this.answerContent = answerContent;
		this.question = question;
	}

	public Answer(Integer answerId, StudentHomework studentHomework, String answerContent,
			Question question) {
		super();
		this.answerId = answerId;
		this.studentHomework = studentHomework;
		this.answerContent = answerContent;
		this.question = question;
	}

	public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

	public Question getQuestion() {
		return question;
	}

	public StudentHomework getStudentHomework() {
		return studentHomework;
	}

	public void setStudentHomework(StudentHomework studentHomework) {
		this.studentHomework = studentHomework;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}