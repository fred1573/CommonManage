package com.frd.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURL().toString();
		String[] urlArr = url.split("/");
		if(urlArr[4].equals("user") || urlArr[4].startsWith("account")){
			if(!urlArr[5].equals("userNameCheck") && !urlArr[5].equals("emailCheck")){
				if(request.getSession().getAttribute("user") != null){
					return true;
				}else{
					String root = null;
					if(request.getSession().getAttribute("root") != null)
						root = request.getSession().getAttribute("root").toString();
					else
						root = request.getContextPath();
					System.out.println(root + "/login");
					response.sendRedirect(root + "/login");
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("---" + ex);
		super.afterCompletion(request, response, handler, ex);
	}

	

	
}
