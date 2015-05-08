package com.ccw.testonline.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.service.CommonService;
import com.ccw.testonline.common.utils.BeanValidation;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.ServiceException;
import com.ccw.testonline.exception.ValidateException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 公共控制层类<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @param <A>
 * @creatdate 2014年11月11日 下午9:09:31 <br>
 *
 */
public interface BaseController<T, B extends BaseBean<T>> {
	
	
	public PageVO<T> findListByPage(PageBean pageBean);
	
	/**
	 * 新增
	 * @return
	 */
	public Map<String,Object> add(B addBean);
	
	/**
	 * 删除
	 * @return
	 */
	public Map<String,Object> delete(@RequestBody List<IdsBean> ids);
	
	/**
	 * getById
	 * @return
	 */
	public Map<String,Object> getById(@PathVariable Integer id);
	
	/**
	 *  编辑信息
	 * @return
	 */
	public Map<String,Object> edit(B editBean);
	
}
