package com.ccw.testonline.service;

import com.ccw.testonline.bean.ClassHwBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.DaoException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 作业业务类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月12日 上午8:47:18 <br>
 *
 */
public interface HomeworkService {

	PageVO<Object> findUnFinishedStudent(ClassHwBean classHwBean,
			PageBean pageBean) throws DaoException;

	PageVO<Object> findFinishedStudent(ClassHwBean classHwBean,
			PageBean pageBean) throws DaoException;
}
