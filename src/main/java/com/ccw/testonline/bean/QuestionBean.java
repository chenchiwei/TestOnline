package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.exception.ValidateException;

public class QuestionBean implements BaseBean<Question> {

	private Integer questionId;

	@NotNull
    private String questionContent;

	@NotNull
    private Integer questionType;

	@NotNull
    private String questionLevel;

	@NotNull
    private Integer chapterId;
	
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
		this.questionContent = questionContent;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getQuestionLevel() {
		return questionLevel;
	}

	public void setQuestionLevel(String questionLevel) {
		this.questionLevel = questionLevel;
	}

	public Integer getChapterId() {
		return chapterId;
	}

	public void setChapterId(Integer chapterId) {
		this.chapterId = chapterId;
	}

	@Override
	public Question toAddEntity() throws ValidateException {
		Question question = null;
		try {
			BeanValidation.validate(this);
			question = new Question();
			question.setQuestionContent(this.questionContent);
			question.setQuestionType(this.questionType);
			question.setQuestionLevel(this.questionLevel);
		} catch (ValidateException e) {
			throw e;
		}
		return question;
	}

	@Override
	public Question toEditEntity(Question question) throws ValidateException {
		try {
			BeanValidation.validate(this);
			question.setQuestionContent(this.questionContent);
			question.setQuestionType(this.questionType);
			question.setQuestionLevel(this.questionLevel);
		} catch (ValidateException e) {
			throw e;
		}
		return question;
	}

	@Override
	public Integer getId() {
		return this.questionId;
	}

}
