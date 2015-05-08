package com.ccw.testonline.exception;

import org.apache.log4j.Logger;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger=Logger.getLogger(this.getClass());
	
	public ServiceException() {
		super();
		logger.error("业务操作异常！");
	}

	public ServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		logger.error("业务操作异常！");
	}

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		logger.error("业务操作异常！");
	}

	public ServiceException(String arg0) {
		super(arg0);
		logger.error("业务操作异常！");
	}

	public ServiceException(Throwable arg0) {
		super(arg0);
		logger.error("业务操作异常！");
	}

}
