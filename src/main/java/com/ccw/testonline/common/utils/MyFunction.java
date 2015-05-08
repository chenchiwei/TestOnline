package com.ccw.testonline.common.utils;

import java.util.Collection;
import java.util.List;

import com.ccw.testonline.entity.Answer;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:自定义jstl标签 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年12月28日 下午12:48:46 <br>
 *
 */
public class MyFunction {
	
	/**
	 * 集合是否包含该元素
	 * @param coll
	 * @param object
	 * @return
	 */
	public static boolean isContain(Collection<Object> coll,Object object){
		return coll.contains(object);
	}
	
	/**
	 * 判断选项内容是否是答案
	 * @param answers
	 * @param str
	 * @return
	 */
	public static boolean isCheckBoxAnswer(List<Answer> answers,String str){
		for (Answer answer : answers) {
			if(answer.getAnswerContent().equals(str)){
				return true;
			}
		}
		return false;
	}
}
