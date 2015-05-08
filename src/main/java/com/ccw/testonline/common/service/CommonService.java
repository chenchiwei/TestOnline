package com.ccw.testonline.common.service;

import java.util.List;
import java.util.Map;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.ServiceException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:公共业务类接口,E代表实体类，V代表Vo对象 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月16日 上午10:35:05 <br>
 *
 */
public interface CommonService<A,B extends BaseBean<A>> {
	
	/**
	 * 分页
	 * @param pageVo
	 * @return
	 */
	public PageVO<A> findListByPage(PageBean pageVo,String sqlNamespace) throws ServiceException;

	/**
	 * 添加
	 * @param 
	 * @return
	 * @throws ServiceException
	 */
	public int add(B vo,String sqlNamespace) throws ServiceException;

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public int delete(List<IdsBean> ids,String sqlNamespace);

	/**
	 * 
	 * @param 
	 * @return
	 */
	public A getById(Integer id,String sqlNamespace);

	/**
	 * 编辑
	 * @param 
	 * @return
	 * @throws ServiceException 
	 */
	public int edit(B vo,String sqlNamespace) throws ServiceException;

	public PageVO<A> findListByPage(PageBean pageVo, String sqlNamespace,
			String executeName) throws ServiceException;

	PageVO<A> findListByPage(PageBean pageVo, String sqlNamespace,
			Map<String, Object> paramMap) throws ServiceException;
	
	PageVO<A> findListByPage(PageBean pageVo, String sqlNamespace,
			Map<String, Object> paramMap,
			String executeName) throws ServiceException;

	List findList(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) throws ServiceException;

	int addAll(List<A> list, String sqlNamespace, String executeName) throws ServiceException;

	int add(A entity, String sqlNamespace) throws ServiceException;

	int add(Object entity, String sqlNamespace, String executeName)
			throws ServiceException;

	int delete(List<IdsBean> ids, String sqlNamespace, String executeName);

	Integer getNum(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) throws ServiceException;

	Object findOne(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) throws ServiceException;

	int edit(Object entity, String sqlNamespace, String executeName)
			throws ServiceException;

	int edit(A entity, String sqlNamespace) throws ServiceException;


	Object findById(Integer id, String sqlNamespace) throws ServiceException;

	PageVO<Object> findListByPage(PageBean pageVo, String sqlNamespace,
			String executeName, int count) throws ServiceException;

	PageVO<Object> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo, String sqlNamespace, String executeName, int count)
			throws ServiceException;
}
