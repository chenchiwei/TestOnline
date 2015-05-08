package com.ccw.testonline.security;

import java.io.Serializable;

/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 用于封装登录基本信息<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2015年1月2日 上午10:50:15 <br>
 *
 */
public class UserBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserBase(String username, String password, String name, Integer type) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.type = type;
	}
	private String username;
	private String password;
	private String name;
	private Integer type;
	private String validateCode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public UserBase(String username, String password, Integer type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}
	public UserBase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
