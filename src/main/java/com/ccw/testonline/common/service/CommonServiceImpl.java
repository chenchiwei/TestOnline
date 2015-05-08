package com.ccw.testonline.common.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.dao.CommonDao;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.DaoException;
import com.ccw.testonline.exception.ServiceException;

@Service("commonService")
@Transactional(readOnly=true)
public class CommonServiceImpl<T> implements CommonService<T, BaseBean<T>> {

	private Logger logger=Logger.getLogger(this.getClass());
	
	@Resource(name="commonDao")
	protected CommonDao<T> commonDao;
	
	public CommonDao<T> getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao<T> commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public PageVO<T> findListByPage(PageBean pageVo,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<T> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(pageVo, sqlNamespace);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}
	
	@Override
	public PageVO<T> findListByPage(PageBean pageVo,String sqlNamespace,String executeName) throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<T> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(pageVo, sqlNamespace,executeName);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}
	
	@Override
	public PageVO<Object> findListByPage(PageBean pageVo,String sqlNamespace,String executeName,int count) throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<Object> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(null, pageVo, null, null, sqlNamespace, executeName, count);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}
	
	@Override
	public PageVO<Object> findListByPage(Map<String,Object> paramMap,PageBean pageVo,String sqlNamespace,String executeName,int count) throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<Object> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(paramMap, pageVo, null, null, sqlNamespace, executeName, count);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}

	@Override
	public PageVO<T> findListByPage(PageBean pageVo,String sqlNamespace,Map<String,Object> paramMap) throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<T> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(paramMap, pageVo, sqlNamespace);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}
	
	@Override
	public List findList(Map<String,Object> paramMap,String sqlNamespace,String executeName)
			throws ServiceException {
		logger.debug("---------开始分页查询---------");
		List list=null;
		list=this.commonDao.findList(paramMap, sqlNamespace, executeName);
		
		logger.debug("---------分页查询结束---------");
		return list;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int add(BaseBean<T> vo,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始添加信息---------");
		int result=0;
		T entity = null;
		try {
			if(vo!=null){
				entity=vo.toAddEntity();
			}
			result=this.commonDao.save(entity, sqlNamespace);
		} catch (Exception e) {
			logger.debug("---------添加信息错误---------");
			throw new ServiceException("添加信息错误！", e);
		}
		logger.debug("---------结束添加信息---------");
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int add(T entity,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始添加信息---------");
		int result=0;
		try {
			if(entity!=null){
				result=this.commonDao.save(entity, sqlNamespace);
			}
		} catch (Exception e) {
			logger.debug("---------添加信息错误---------");
			throw new ServiceException("添加信息错误！", e);
		}
		logger.debug("---------结束添加信息---------");
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int add(Object entity,String sqlNamespace,String executeName) throws ServiceException {
		logger.debug("---------开始添加信息---------");
		int result=0;
		try {
			if(entity!=null){
				result=this.commonDao.save(entity, sqlNamespace,executeName);
			}
		} catch (Exception e) {
			logger.debug("---------添加信息错误---------");
			throw new ServiceException("添加信息错误！", e);
		}
		logger.debug("---------结束添加信息---------");
		return result;
	}

	@Override
	@Transactional(readOnly=false)
	public int addAll(List<T> list,String sqlNamespace,String executeName) throws ServiceException{
		logger.debug("---------开始添加信息---------");
		int result=0;
		try {
			for(T obj:list){
				result+=this.commonDao.save(obj, sqlNamespace, executeName);
			}
		} catch (Exception e) {
			logger.debug("---------添加信息错误---------");
			throw new ServiceException("添加信息错误！", e);
		}
		logger.debug("---------结束添加信息---------");
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int delete(List<IdsBean> ids,String sqlNamespace) {
		logger.debug("---------开始删除---------");
		int flag=0;
		if(ids.size()>0){
			flag=this.commonDao.deleteByIds(ids,sqlNamespace);
		}
		logger.debug("---------结束删除---------");
		return flag;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int delete(List<IdsBean> ids,String sqlNamespace,String executeName) {
		logger.debug("---------开始删除---------");
		int flag=0;
		if(ids.size()>0){
			flag=this.commonDao.deleteByIds(ids,sqlNamespace,executeName);
		}
		logger.debug("---------结束删除---------");
		return flag;
	}

	@Override
	public T getById(Integer id,String sqlNamespace) {
		logger.debug("---------开始通过id获取信息---------");
		T entity=null;
		if(id!=null){
			entity=this.commonDao.findById(id,sqlNamespace);
		}
		
		logger.debug("---------结束通过id获取信息---------");
		return entity;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int edit(BaseBean<T> vo,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始编辑信息---------");
		int result=0;
		T entity=null;
		try {
			Integer id=null;
			if((id=vo.getId())!=null){
				entity=this.commonDao.findById(id, sqlNamespace);
			}
			entity=vo.toEditEntity(entity);
			result=this.commonDao.update(entity, sqlNamespace);
			
		} catch (Exception e) {
			logger.debug("---------编辑信息错误---------");
			throw new ServiceException("编辑信息错误！", e);
		}
		logger.debug("---------结束编辑信息---------");
		return result;
	}
	
	@Override
	@Transactional(readOnly=true)
	public Object findById(Integer id,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始信息---------");
		Object entity=null;
		try {
				entity=this.commonDao.findById(id, sqlNamespace);
			
		} catch (Exception e) {
			logger.debug("---------信息错误---------");
			throw new ServiceException("信息错误！", e);
		}
		logger.debug("---------结束信息---------");
		return entity;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int edit(T entity,String sqlNamespace) throws ServiceException {
		logger.debug("---------开始编辑信息---------");
		int result=0;
		try {
			result=this.commonDao.update(entity, sqlNamespace);
			
		} catch (Exception e) {
			logger.debug("---------编辑信息错误---------");
			throw new ServiceException("编辑信息错误！", e);
		}
		logger.debug("---------结束编辑信息---------");
		return result;
	}
	
	@Override
	@Transactional(readOnly=false)
	public int edit(Object entity,String sqlNamespace,String executeName) throws ServiceException {
		logger.debug("---------开始编辑信息---------");
		int result=0;
		try {
			result=this.commonDao.update(entity, sqlNamespace,executeName);
			
		} catch (Exception e) {
			logger.debug("---------编辑信息错误---------");
			throw new ServiceException("编辑信息错误！", e);
		}
		logger.debug("---------结束编辑信息---------");
		return result;
	}

	@Override
	public PageVO<T> findListByPage(PageBean pageVo, String sqlNamespace,
			Map<String, Object> paramMap, String executeName)
			throws ServiceException {
		logger.debug("---------开始分页查询---------");
		PageVO<T> pageBean=null;
		try {
			pageBean=this.commonDao.findListByPage(paramMap, pageVo, sqlNamespace, executeName);
		} catch (DaoException e) {
			throw new ServiceException("分页查询信息失败！", e);
		}
		
		logger.debug("---------分页查询结束---------");
		return pageBean;
	}
	
	@Override
	public Integer getNum(Map<String, Object> paramMap,String sqlNamespace,
			 String executeName)
			throws ServiceException {
		logger.debug("---------开始查询数量---------");
		Integer num=null;
		try {
			num=this.commonDao.getNum(paramMap, sqlNamespace, executeName);
		} catch (Exception e) {
			throw new ServiceException("查询数量失败！", e);
		}
		
		logger.debug("---------查询数量结束---------");
		return num;
	}
	
	@Override
	public Object findOne(Map<String, Object> paramMap,String sqlNamespace,
			 String executeName)
			throws ServiceException {
		logger.debug("---------开始查询---------");
		Object obj=null;
		try {
			obj=this.commonDao.findOne(paramMap, sqlNamespace, executeName);
		} catch (Exception e) {
			throw new ServiceException("查询数量失败！", e);
		}
		
		logger.debug("---------查询结束---------");
		return obj;
	}

	
}
