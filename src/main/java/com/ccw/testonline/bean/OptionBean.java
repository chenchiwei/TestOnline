package com.ccw.testonline.bean;

import javax.validation.constraints.NotNull;

import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.entity.Options;
import com.ccw.testonline.entity.Question;
import com.ccw.testonline.exception.ValidateException;

public class OptionBean implements BaseBean<Options> {

	private Integer optionId;

	@NotNull
    private String optionContent;

	@NotNull
    private Integer questionId;

	public Integer getOptionId() {
		return optionId;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public Options toAddEntity() throws ValidateException {
		Options option = null;
		try {
			BeanValidation.validate(this);
			option = new Options();
			option.setOptionContent(this.optionContent);
			Question question=null;
			if(this.questionId!=null){
				question=new Question();
				question.setQuestionId(this.questionId);
			}
			option.setQuestion(question);
		} catch (ValidateException e) {
			throw e;
		}
		return option;
	}

	@Override
	public Options toEditEntity(Options option) throws ValidateException {
		try {
			BeanValidation.validate(this);
			option.setOptionContent(this.optionContent);
			Question question=null;
			if(this.questionId!=null){
				question=new Question();
				question.setQuestionId(this.questionId);
			}
			option.setQuestion(question);
		} catch (ValidateException e) {
			throw e;
		}
		return option;
	}

	@Override
	public Integer getId() {
		return this.optionId;
	}

}
