package com.ccw.testonline.common.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.ccw.testonline.entity.Options;
import com.ccw.testonline.entity.Question;

public class GenerateScoreUtils {
	
	public static float generate(List<Question> list,
			List<Question> listTwo) throws IOException {
		float score=0;
		for (Question question : list) {
			// 如果是单选
			if (question.getQuestionType() == 1) {
				float s = question.getHomeWorkQuestions().get(0).getScore();
				// 学生选的那个
				Integer stuOptId = Integer.parseInt(question.getAnswer()
						.getAnswerContent());
				String queAns = question.getQuestionAnswer();
				// 找到答案所在选项的ID
				Integer answerOptId = null;
				for (Options option : question.getOptions()) {
					if (option.getOptionContent().toUpperCase().trim()
							.startsWith(queAns.toUpperCase().trim())) {
						answerOptId = option.getOptionId();
					}
				}
				// 如果选对了，则加分
				if (stuOptId == answerOptId) {
					score += s;
				}
			}
			// 如果是多选
			if (question.getQuestionType() == 2) {
				// 学生选的那些
				String[] answerStr = question.getAnswer()
						.getAnswerContent().split(",");
				Integer[] answerIds=new Integer[answerStr.length];
				//转类型
				for (int i = 0; i < answerStr.length; i++) {
					answerIds[i]=Integer.parseInt(answerStr[i]);
				}

				String[] optStr = question.getQuestionAnswer().split(",");
				Integer[] optIds = new Integer[optStr.length];
				for (Options option : question.getOptions()) {
					for (int i = 0; i < optStr.length; i++) {
						if (option.getOptionContent().toUpperCase().trim()
								.startsWith(optStr[i].toUpperCase().trim())) {
							optIds[i] = option.getOptionId();
						}
					}
				}
				boolean flag=true;
				List idList=Arrays.asList(answerIds);
				for (int i = 0; i < optIds.length; i++) {
					//有一个没选上都算错
					if(!idList.contains(optIds[i])){
						flag=false;
					}
				}
				float s = question.getHomeWorkQuestions().get(0).getScore();
				if(flag){
					score+=s;
				}

			}
		}
		
		for (Question question2 : listTwo) {
			//取出回答的文本
			String stuAnswer=question2.getAnswer().getAnswerContent();
			float s = question2.getHomeWorkQuestions().get(0).getScore();
			String queAnswer=question2.getQuestionAnswer();
			
			//文本相似度分析
			double similarity=TextMatchUtils.caculateSimiarity(stuAnswer, queAnswer);
			//如果匹配不对，说明两者相差很大
			if(!Double.isNaN(similarity)){
				float s1=(float) (s*similarity);
				score+=s1;
			}
			
		}
		return score;
	}
}
