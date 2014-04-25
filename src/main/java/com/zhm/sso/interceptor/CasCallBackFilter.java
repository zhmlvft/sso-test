package com.zhm.sso.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zhm.sso.model.UserInfo;
import com.zhm.sso.service.UserService;

public class CasCallBackFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		ApplicationContext ac1 =
	   			 WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		String url = req.getRequestURI();
		if(url.indexOf("login")!=-1)
		{
			chain.doFilter(request, response);
		}
		else
		{
		    PrincipalCollection principalCollection = SecurityUtils.getSubject()
			         .getPrincipals();
	    	Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			Object user = session.getAttribute("currUser");
			if (principalCollection != null) {    
		    	 List principals = principalCollection.asList();
		    	 // 这里获取到的list有两个元素，
		    	 //一个是cas返回来的用户名，举例是aaa，
		    	 //一个是cas返回的更多属性的map对象，举例是{uid:aaa,username:aaa,email:aaa}
		    	 //通过principals.get(1)来获得属性集合的map对象
		    	 String username =  (String)principals.get(0);
				 if(user==null)
				 {
			    	 UserService userService = (UserService)ac1.getBean("userService");
			    	 UserInfo loginUser = userService.findUserByCond(username);
			    	 session.setAttribute("currUser", loginUser);
				 }
				 else
				 {
					 UserInfo currUser = (UserInfo)user;
			    	 if(currUser.getId().equals(username))
			    	 {
			    		 //同一个账号
			    	 }
			    	 else{
			    		 //不同账号覆盖原有的session
			    		 UserService userService = (UserService)ac1.getBean("userService");
				    	 UserInfo loginUser = userService.findUserByCond(username);
				    	 session.setAttribute("currUser", loginUser);
			    	 }
			     }
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
