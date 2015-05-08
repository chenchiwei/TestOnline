package com.ccw.testonline.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.ccw.testonline.exception.ValidateException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 校验类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月11日 下午9:33:13 <br>
 *
 */
public class BeanValidation {
	 private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory(); 
	 
	 /**
	  * 检验bean
	  * @param obj
	  * @return
	 * @throws ValidateException 
	  */
	 public static boolean validate(Object obj) throws ValidateException{
		 try {
			Validator validator = vf.getValidator(); 
			 Set<ConstraintViolation<Object>> set = validator.validate(obj); 
			 if(set!=null&&set.size()==0){
				 return true;
			 }
			 return false;
		} catch (Exception e) {
			throw new ValidateException("校验出错!",e);
		}
	 }
}
