package com.ccw.testonline.entity;

import java.util.ArrayList;
import java.util.List;

public class Question {
    public Question() {
		super();
	}

	public Question(Integer questionId) {
		super();
		this.questionId = questionId;
	}

	private Integer questionId;

    private String questionContent;

    private Integer questionType;

    private String questionLevel;

    private String questionAnswer;

    private Course course;
    
    private List<Options> options=new ArrayList<Options>();
    
    private Answer answer;
    
    private List<Answer> answers;
    
    private List<HomeWorkQuestion> homeWorkQuestions=new ArrayList<>();
    
    //为返回数据所用
    private Float score;
    
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent == null ? null : questionContent.trim();
    }

    public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Course getCourse() {
		return course;
	}

	public List<HomeWorkQuestion> getHomeWorkQuestions() {
		return homeWorkQuestions;
	}

	public void setHomeWorkQuestions(List<HomeWorkQuestion> homeWorkQuestions) {
		this.homeWorkQuestions = homeWorkQuestions;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType == null ? null : questionType;
    }

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel == null ? null : questionLevel.trim();
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer == null ? null : questionAnswer.trim();
    }

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public void formateScore(){
		if(this.questionType==1){
			this.score=5f;
		}else if(this.questionType==2){
			this.score=5f;
		}else{
			this.score=15f;
		}
	}
}