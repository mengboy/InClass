package com.swjtu.cn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.swjtu.cn.utils.ActionUtil;

public class LoginInterceptor implements HandlerInterceptor{
	Log logger = LogFactory.getLog(LoginInterceptor.class);
	public static String[] ignore_jsp = {"login.html"};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		// TODO Auto-generated method stub
		boolean flag = true;
		String request_url = request.getRequestURL().toString();
		System.out.println("requesturl:"+request_url);
		for(String str:ignore_jsp){
			if(request_url.indexOf(str) == -1&&request_url.indexOf(".html") != -1){
				logger.info(request.getSession().getAttribute(ActionUtil.SESSION_ADMIN) == null);
				if(ActionUtil.getCurrentAdmin(request) == null){
					flag = false;
					response.sendRedirect(request.getContextPath()+"/production/login.html");
				}
			}
		}
		return flag;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
