package com.imooc.o2o.interceptor.shopadmin;

import com.imooc.o2o.entity.Shop;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:48 2018/5/23
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 事前拦截 用户操作前
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// GET CURRENT SHOP FROM SESSION
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// GET CURRENT OPERATOR SHOP_LIST FROM SESSION
		@SuppressWarnings("unchecked")
		List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
		if(currentShop != null && shopList != null){
			for(Shop shop : shopList){
				if(shop.getShopId() == currentShop.getShopId()){
					return true;
				}
			}
		}
		return false;
	}
}
