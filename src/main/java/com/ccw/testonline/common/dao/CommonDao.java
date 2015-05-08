package com.ccw.testonline.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.DaoException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:公共数据访问层接口 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月11日 下午3:43:54 <br>
 *
 */
public interface CommonDao<T> {

	/**
	 * 保存实体
	 * @param entity
	 * @return 正确执行的记录
	 */
	public int save(T entity,String sqlNamespace);
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	public int delete(T entity);
	
	/**
	 * 更新
	 * @param entity
	 * @return
	 */
	public int update(T entity,String sqlNamespace);
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	public int deleteById(Serializable id,String sqlNamespace);
	
	/**
	 * 通过ids删除
	 * @param id
	 * @return
	 */
	public int deleteByIds(List<IdsBean> ids,String sqlNamespace);
	
	/**
	 * 通过id查找实体
	 * @param id
	 * @return
	 */
	public T findById(Serializable id,String sqlNamespace);
	
	/**
	 * 查找所有记录
	 * @return
	 */
	public List<T> findAll(String sqlNamespace);
	
	/**
	 * 通过参数查询记录
	 * @param paramMap
	 * @return
	 */
	public List<T> findList(Map<String, Object> paramMap);
	
	/**
	 * 分页查询
	 * @param paramMap 参数
	 * @param pageNum 页码
	 * @param pageSize 页大小
	 * @param sort 排序
	 * @param dir 方向
	 * @return
	 * @throws DaoException 
	 */
	public PageVO<T> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo,String sort,String dir,String sqlNamespace,String executeName) throws DaoException;
	
	/**
	 * 分页查询
	 * @param pageVo
	 * @return
	 * @throws DaoException
	 */
	public PageVO<T> findListByPage(PageBean pageVo,String sqlNamespace) throws DaoException;
	
	public PageVO<T> findListByPage(PageBean pageVo,String sqlNamespace,String executeName) throws DaoException;
	
	/**
	 * 分页查询
	 * @param paramMap
	 * @param pageVo
	 * @return
	 * @throws DaoException
	 */
	public PageVO<T> findListByPage(Map<String, Object> paramMap, PageBean pageVo,String sqlNamespace) throws DaoException;
	
	public PageVO<T> findListByPage(Map<String, Object> paramMap, PageBean pageVo,String sqlNamespace,String executeName) throws DaoException;
	
	/**
	 * 获取列表数目
	 * @param paramMap
	 * @return
	 */
	public int getCount(Map<String, Object> paramMap,String sqlNamespace);

	List<T> findAll(String sqlNamespace, String executeName);

	List<T> findList(Map<String, Object> paramMap, String sqlNamespace,
			String executeName);

	int save(Object obj, String sqlNamespace, String executeName);

	int deleteById(Serializable id, String sqlNamespace, String executeName);

	int deleteByIds(List<IdsBean> ids, String sqlNamespace, String executeName);

	int getNum(Map<String, Object> paramMap, String sqlNamespace,
			String executeName);

	Object findOne(Map<String, Object> paramMap, String sqlNamespace,
			String executeName);

	PageVO<Object> findListByPage(Map<String, Object> paramMap, PageBean pageVo,
			String sort, String dir, String sqlNamespace, String executeName,
			int count) throws DaoException;

	int update(Object entity, String sqlNamespace, String executeName);
	
}
