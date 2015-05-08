package com.ccw.testonline.bean;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description:用于接收分页参数 <br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月14日 下午4:38:47 <br>
 *
 */
public class PageBean {
	private String sort;
	private String order;
	private Integer page;
	private Integer rows;
	
	public PageBean() {
		super();
	}
	public PageBean(String sort, String order, Integer page, Integer rows) {
		super();
		this.sort = sort;
		this.order = order;
		this.page = page;
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
