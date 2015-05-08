package com.ccw.testonline.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ccw.testonline.bean.IdsBean;
import com.ccw.testonline.bean.PageBean;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.DaoException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:公共数据访问层 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月11日 下午3:44:18 <br>
 * 
 */
@Repository("commonDao")
public class CommonDaoImpl<T> extends SqlSessionDaoSupport implements
		CommonDao<T> {

	/**
	 * 常量
	 */
	public static final String SQL_SAVE = "insertSelective";
	public static final String SQL_UPDATE = "updateByPrimaryKeySelective";
	public static final String SQL_DELETEBYID = "deleteByPrimaryKey";
	public static final String SQL_FINDBYID = "selectByPrimaryKey";

	public static final String SQL_DELETEBYIDS = "deleteByIds";
	public static final String SQL_FINDPAGE = "findListByPage";
	public static final String SQL_FINDLISTBY = "findList";
	public static final String SQL_FINDALL = "findAll";
	public static final String SQL_GETCOUNT = "getCount";

	private static final String SORT_NAME = "SORT";

	private static final String DIR_NAME = "DIR";

	public static final String SQLNAME_SEPARATOR = ".";

	/** 不能用于中的非法字符（主要用于排序字段名） */
	public static final String[] ILLEGAL_CHARS_FOR_SQL = { ",", ";", " ", "\"",
			"%" };

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	/**
	 * 获取泛型类中的命名空间
	 * 
	 * @return
	 */
	/*
	 * protected String getDefaultSqlNamespace() {
	 * 
	 * @SuppressWarnings("unchecked") Class<T> clazz =
	 * GenericsUtils.getSuperClassGenricType(this.getClass()); String nameSpace
	 * = clazz.getName();
	 * System.out.println(nameSpace+"++++++++++++++++++++++++++++++"); return
	 * nameSpace; }
	 *//**
	 * 命名空间
	 */
	/*
	 * private String sqlNamespace=getDefaultSqlNamespace();
	 * 
	 * public String getSqlNamespace() { return sqlNamespace; }
	 * 
	 * public void setSqlNamespace(String sqlNamespace) { this.sqlNamespace =
	 * sqlNamespace; }
	 */
	/**
	 * 获取statement
	 */
	public String getStatement(String sqlNamespace, String sqlOperate) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlOperate;
	}

	@Override
	public int save(T entity, String sqlNamespace) {
		return this.getSqlSession().insert(
				this.getStatement(sqlNamespace, SQL_SAVE), entity);
	}

	@Override
	public int save(Object entity, String sqlNamespace, String executeName) {
		return this.getSqlSession().insert(
				this.getStatement(sqlNamespace, executeName), entity);
	}

	@Override
	public int delete(T entity) {
		return 0;
	}

	@Override
	public int update(T entity, String sqlNamespace) {
		return this.getSqlSession().update(
				this.getStatement(sqlNamespace, SQL_UPDATE), entity);
	}
	
	@Override
	public int update(Object entity, String sqlNamespace,
			String executeName) {
		return this.getSqlSession().update(
				this.getStatement(sqlNamespace, executeName), entity);
	}

	@Override
	public int deleteById(Serializable id, String sqlNamespace) {
		return this.getSqlSession().delete(
				this.getStatement(sqlNamespace, SQL_DELETEBYID), id);
	}

	@Override
	public int deleteById(Serializable id, String sqlNamespace,
			String executeName) {
		return this.getSqlSession().delete(
				this.getStatement(sqlNamespace, executeName), id);
	}

	@Override
	public int deleteByIds(List<IdsBean> ids, String sqlNamespace) {
		int flag = 0;
		if (ids.size() > 0) {
			for (IdsBean id : ids) {
				flag += this.deleteById(id.getId(), sqlNamespace);
			}
		}
		return flag;
	}

	@Override
	public int deleteByIds(List<IdsBean> ids, String sqlNamespace,
			String executeName) {
		int flag = 0;
		if (ids.size() > 0) {
			for (IdsBean id : ids) {
				flag += this.deleteById(id.getId(), sqlNamespace, executeName);
			}
		}
		return flag;
	}

	@Override
	public T findById(Serializable id, String sqlNamespace) {
		return this.getSqlSession().selectOne(
				this.getStatement(sqlNamespace, SQL_FINDBYID), id);
	}

	@Override
	public Object findOne(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) {
		return (Object) this.getSqlSession().selectOne(
				this.getStatement(sqlNamespace, executeName), paramMap);
	}

	@Override
	public List<T> findAll(String sqlNamespace, String executeName) {
		return this.getSqlSession().selectList(
				this.getStatement(sqlNamespace, executeName));
	}

	@Override
	public List<T> findAll(String sqlNamespace) {
		return this.getSqlSession().selectList(
				this.getStatement(sqlNamespace, SQL_FINDALL));
	}

	@Override
	public List<T> findList(Map<String, Object> paramMap) {
		return this.getSqlSession().selectList(SQL_FINDLISTBY, paramMap);
	}

	@Override
	public List<T> findList(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) {
		return this.getSqlSession().selectList(
				this.getStatement(sqlNamespace, executeName), paramMap);
	}

	public PageVO<T> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo, String sort, String dir, String sqlNamespace,
			String executeName) throws DaoException {
		// 获取满足条件的记录总数，没有记录时返回空页数据
		PageVO<T> pageBean = null;
		try {
			int count = getCount(paramMap, sqlNamespace);
			if (count < 1) {
				return null;
			}
			// 排序条件
			if (sort != null) {
				// 排序字段不为空，过滤其中可能存在的非法字符
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {
				// paramMap.put("sort", null);
				// paramMap.put("dir", null);
			} else {
				paramMap.put(SORT_NAME, sort);
				paramMap.put(DIR_NAME, dir);
			}
			// 分页条件
			int start = (pageVo.getPage() - 1) * pageVo.getRows();
			if (start < 1)
				start = 0;

			RowBounds rowBound = new RowBounds(start, pageVo.getRows());

			List<T> list = this.getSqlSession()
					.selectList(getStatement(sqlNamespace, executeName),
							paramMap, rowBound);
			pageBean = new PageVO<>(count, list);
		} catch (Exception e) {
			throw new DaoException("分页查询出错！", e);
		}
		return pageBean;
	}

	@Override
	public PageVO<Object> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo, String sort, String dir, String sqlNamespace,
			String executeName, int count) throws DaoException {
		// 获取满足条件的记录总数，没有记录时返回空页数据
		PageVO<Object> pageBean = null;
		;
		try {
			if (count < 1) {
				return null;
			}
			// 排序条件
			if (sort != null) {
				// 排序字段不为空，过滤其中可能存在的非法字符
				sort = filterIllegalChars(sort, ILLEGAL_CHARS_FOR_SQL);
			}
			if (StringUtils.isEmpty(sort) || StringUtils.isEmpty(dir)) {
				// paramMap.put("sort", null);
				// paramMap.put("dir", null);
			} else {
				paramMap.put(SORT_NAME, sort);
				paramMap.put(DIR_NAME, dir);
			}
			// 分页条件
			int start = (pageVo.getPage() - 1) * pageVo.getRows();
			if (start < 1)
				start = 0;

			RowBounds rowBound = new RowBounds(start, pageVo.getRows());

			List<T> list = this.getSqlSession()
					.selectList(getStatement(sqlNamespace, executeName),
							paramMap, rowBound);
			pageBean = new PageVO<Object>(count, (List<Object>) list);
		} catch (Exception e) {
			throw new DaoException("分页查询出错！", e);
		}
		return pageBean;
	}

	public int getCount(Map<String, Object> paramMap, String sqlNamespace) {
		return (Integer) this.getSqlSession().selectOne(
				getStatement(sqlNamespace, SQL_GETCOUNT), paramMap);
	}

	@Override
	public int getNum(Map<String, Object> paramMap, String sqlNamespace,
			String executeName) {
		return (Integer) this.getSqlSession().selectOne(
				getStatement(sqlNamespace, executeName), paramMap);
	}

	public PageVO<T> findListByPage(PageBean pageVo, String sqlNamespace)
			throws DaoException {
		return this.findListByPage(null, pageVo, null, null, sqlNamespace,
				SQL_FINDPAGE);
	}

	public PageVO<T> findListByPage(PageBean pageVo, String sqlNamespace,
			String executeName) throws DaoException {
		return this.findListByPage(null, pageVo, null, null, sqlNamespace,
				executeName);
	}

	public PageVO<T> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo, String sqlNamespace) throws DaoException {
		return this.findListByPage(paramMap, pageVo, null, null, sqlNamespace,
				SQL_FINDPAGE);
	}

	public PageVO<T> findListByPage(Map<String, Object> paramMap,
			PageBean pageVo, String sqlNamespace, String executeName)
			throws DaoException {
		return this.findListByPage(paramMap, pageVo, null, null, sqlNamespace,
				executeName);
	}

	/**
	 * 过滤非法字符
	 * 
	 * @param str
	 * @param filterChars
	 * @return
	 */
	protected String filterIllegalChars(String str, String[] filterChars) {
		String rs = str;
		if (rs != null && filterChars != null) {
			for (String fc : filterChars) {
				if (fc != null && fc.length() > 0) {
					str = str.replaceAll(fc, "");
				}
			}
		}
		return rs;
	}

}
