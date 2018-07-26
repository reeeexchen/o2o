package com.imooc.o2o.interceptor.shopadmin;

import com.imooc.o2o.entity.PersonInfo;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author:REX
 * @Date: Create in 23:39 2018/5/23
 */
public class ShopLoginInterceptor extends HandlerInterceptorAdapter {
	/**
	 * 事前拦截 用户操作发生前
	 *
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// GET USERINFO FROM SESSION
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			// TRANSFER USERINFO FROM SESSION TO PERSONINFO ENTITY
			PersonInfo user = (PersonInfo) userObj;
			// MAKE SURE USER != NULL && STATUS == 1 AND USERTYPE IS OWNER
			if (user != null && user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() == 1) {
				return true;
			}
		}
		// DOESN'T PASS THE VERIFY && REDIRECT TO LOGIN PAGE
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.location.href='" + request.getContextPath() + "/local/login?usertype=2'");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
}
