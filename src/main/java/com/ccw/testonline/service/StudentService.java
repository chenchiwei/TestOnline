package com.ccw.testonline.service;

import com.ccw.testonline.bean.StudentBean;
import com.ccw.testonline.entity.Student;
import com.ccw.testonline.entity.Teacher;
import com.ccw.testonline.exception.ServiceException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 学生用户业务类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月12日 上午8:47:18 <br>
 *
 */
public interface StudentService {
	

	/**
	 * 管理员编辑学生信息
	 * @param editVo
	 * @return
	 * @throws ServiceException 
	 */
	public int editforadmin(StudentBean editVo) throws ServiceException;

	Student getByStuId(Integer Id);
}
