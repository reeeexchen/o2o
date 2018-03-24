package com.imooc.o2o.util;

/**
 * @Author:REX
 * @Date: Create in 14:45 2018/3/23
 */
public class PageCalculator {
	public static int calculateRowIndex(int pageIndex,int pageSize){
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
