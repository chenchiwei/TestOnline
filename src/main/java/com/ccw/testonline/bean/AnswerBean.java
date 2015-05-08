package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Answer;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.exception.ValidateException;

public class AnswerBean implements BaseBean<Answer> {

	private Integer answerId;

	@NotNull
    private String answerContent;

	@NotNull
    private Integer questionId;
    
	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionid) {
		this.questionId = questionid;
	}

	@Override
	public Answer toAddEntity() throws ValidateException {
		Answer answer = null;
		try {
			BeanValidation.validate(this);
			answer = new Answer();
			answer.setAnswerContent(this.answerContent);
			Question question=null;
			if(this.questionId!=null){
				question=new Question();
				question.setQuestionId(this.questionId);
			}
			answer.setQuestion(question);
		} catch (ValidateException e) {
			throw e;
		}
		return answer;
	}

	@Override
	public Answer toEditEntity(Answer answer) throws ValidateException {
		try {
			BeanValidation.validate(this);
			answer.setAnswerContent(this.answerContent);
			Question question=null;
			if(this.questionId!=null){
				question=new Question();
				question.setQuestionId(this.questionId);
			}
			answer.setQuestion(question);
		} catch (ValidateException e) {
			throw e;
		}
		return answer;
	}

	@Override
	public Integer getId() {
		return this.answerId;
	}

}
