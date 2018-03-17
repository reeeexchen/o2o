package com.imooc.o2o.exception;

/**
 * @Author:REX
 * @Date: Create in 16:46 2018/3/17
 */
public class ShopOperationException extends RuntimeException {

	public ShopOperationException(String message) {
		super(message);
	}

	public ShopOperationException(String message, Throwable cause) {
		super(message, cause);
	}
}
