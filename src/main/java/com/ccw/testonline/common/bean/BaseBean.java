package com.ccw.testonline.common.bean;

import com.ccw.testonline.exception.ValidateException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:此接口用于后面业务层的添加和编辑操作，如果需要对进行修改的话必须要实现 <br>
 * 从前端接受数据的bean要实现这接口，T为实体 
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月16日 下午1:26:25 <br>
 *
 */
public interface BaseBean<T> {
	
	/**
	 * 转换成实体类
	 * @return
	 * @throws ValidateException 
	 */
	public T toAddEntity() throws ValidateException;
	
	/**
	 * 转换成实体类
	 * @return
	 * @throws ValidateException 
	 */
	public T toEditEntity(T entity) throws ValidateException;
	
	/**
	 * 返回id，用于编辑
	 * @return
	 */
	public Integer getId();
}
