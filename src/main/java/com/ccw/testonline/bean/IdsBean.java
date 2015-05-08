package com.ccw.testonline.bean;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 用来接收id数据<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年11月15日 下午4:26:47 <br>
 *
 */
public class IdsBean {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public IdsBean(Integer id) {
		super();
		this.id = id;
	}

	public IdsBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "IdsVO [id=" + id + "]";
	}
	
}
