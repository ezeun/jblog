package jblog.security;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		if(authUser == null) { // 로그인 안된 경우
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
		String blogId = request.getRequestURI().split("/")[2];
		if(!authUser.getId().equals(blogId)) { // 현재 로그인 한 사용자 != 접근하려는 블로그 관리페이지 주인
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
		
		return true;
	}

}
