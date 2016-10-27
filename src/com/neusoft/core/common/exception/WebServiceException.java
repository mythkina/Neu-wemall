package com.neusoft.core.common.exception;

/**
 * @ClassName: WebServiceException
 * @Description: webservice异常类
 * @author neusoft
 * @date 2015年9月9日 下午1:34:38
 * 
 */
public class WebServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public WebServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebServiceException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WebServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WebServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
