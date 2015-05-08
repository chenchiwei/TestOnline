package com.ccw.testonline.service;

import java.util.Map;

import com.ccw.testonline.entity.Teacher;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 教师用户业务类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月12日 上午8:47:18 <br>
 *
 */
public interface TeacherService {
	
	public Teacher getByTeaId(Integer Id);
	
	Object getBy(Map<String, Object> paramMap, String sqlNamespace,
			String exectueName);
	
}
