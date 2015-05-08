package com.ccw.testonline.security;

import org.apache.shiro.authc.UsernamePasswordToken;

public class TestOnlineToken extends UsernamePasswordToken  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String valideteCode;//验证码
	
	private Integer type;//类型
	
	public TestOnlineToken(String username, String password,String valideteCode,Integer type) {
		super(username, password);
		this.valideteCode=valideteCode;
		this.type=type;
	}

	public String getValideteCode() {
		return valideteCode;
	}

	public void setValideteCode(String valideteCode) {
		this.valideteCode = valideteCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
