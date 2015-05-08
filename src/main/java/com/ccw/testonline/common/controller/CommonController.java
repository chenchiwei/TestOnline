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
import com.ccw.testonline.bean.ResultBean;
import com.ccw.testonline.common.bean.BaseBean;
import com.ccw.testonline.common.service.CommonService;
import com.ccw.testonline.common.utils.GenericsUtils;
import com.ccw.testonline.common.vo.PageVO;
import com.ccw.testonline.exception.ServiceException;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:公共controller <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月17日 上午9:05:40 <br>
 *
 */
public class CommonController<T, B extends BaseBean<T>> {

	protected Logger logger=Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	protected CommonService<T, BaseBean<T>> commonService;
	
	protected String STATEMENT=getSqlNamespace();
	
	public String getSTATEMENT() {
		return STATEMENT;
	}

	public void setSTATEMENT(String sTATEMENT) {
		STATEMENT = sTATEMENT;
	}
	
	private String getSqlNamespace(){
		Class<T> clazz=GenericsUtils.getSuperClassGenricType(this.getClass());
		logger.info(clazz.getName());
		return clazz.getName();
	}

	@RequestMapping("/list")
	@ResponseBody
	public PageVO<T> findListByPage(PageBean pageBean){
		PageVO<T> pageVo=null;
		try {
			pageVo=this.commonService.findListByPage(pageBean,STATEMENT);
		} catch (ServiceException e) {
			logger.error("分页查询信息失败！");
			e.printStackTrace();
		}
		
		return pageVo;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<ResultBean> findAll(){
		List<ResultBean> findList=null;
		try {
			findList = commonService.findList(null, STATEMENT, "findAll");
		} catch (ServiceException e) {
			logger.error("查询信息失败！");
			e.printStackTrace();
		}
		
		return findList;
	}
	
	/**
	 * 新增
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String,Object> add(B addBean){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int flag=this.commonService.add(addBean,STATEMENT);
			if(flag>0){
				result.put("success", true);
			}
		} catch (ServiceException e) {
			logger.error("添加信息失败！");
			result.put("success", false);
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 删除
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(@RequestBody List<IdsBean> ids){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			if(ids.size()==0){
				result.put("success", false);
				return result;
			}
				
			int flag=this.commonService.delete(ids,STATEMENT);
			if(flag>0){
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("删除信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/**
	 * getById
	 * @return
	 */
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public Map<String,Object> getById(@PathVariable Integer id){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			if(id==null){
				result.put("success", false);
				return result;
			}
				
			T entity=this.commonService.getById(id,STATEMENT);
			if(entity!=null){
				result.put("success", true);
				result.put("data", entity);
			}
		} catch (Exception e) {
			logger.error("删除信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
	
	/**
	 *  编辑信息
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Map<String,Object> edit(B editBean){
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			int flag=this.commonService.edit(editBean,STATEMENT);
			if(flag>0){
				result.put("success", true);
			}
		} catch (Exception e) {
			logger.error("编辑信息失败！");
			result.put("success", false);
			e.printStackTrace();
		} 
		
		return result;
	}
}
