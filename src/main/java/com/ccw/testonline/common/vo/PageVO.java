package com.ccw.testonline.common.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 分页返回专用<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月11日 下午8:35:11 <br>
 *
 */
public class PageVO<T> {
	
	int total;
	List<T> rows = new ArrayList<T>();
	
	public PageVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageVO(int total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
