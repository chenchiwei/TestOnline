package com.ccw.testonline.exception;

import org.apache.log4j.Logger;

public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Logger logger=Logger.getLogger(this.getClass());
	
	public DaoException() {
		super();
		logger.error("数据访问异常！");
	}

	public DaoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		logger.error("数据访问异常！");
	}

	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		logger.error("数据访问异常！");
	}

	public DaoException(String arg0) {
		super(arg0);
		logger.error("数据访问异常！");
	}

	public DaoException(Throwable arg0) {
		super(arg0);
		logger.error("数据访问异常！");
	}

}
