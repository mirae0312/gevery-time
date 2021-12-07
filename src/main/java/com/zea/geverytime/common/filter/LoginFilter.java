package com.zea.geverytime.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zea.geverytime.member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = 
{"/member/memberlogin", "/board/boardForm","/board/boardCommentEnroll","/board/boardCommentEnroll",
"/myPage/myPageMain", "/board/boardView", "/customer/qnaBoardList", "/info/Enroll", "/product/boardForm", "/productSale/getProduct", "/common/report", "/ugGoods/boardForm",
"/wishList/main", "/cart/main", "/product/onsaleProduct", "/product/productForm", "/common/report", "/chat/chatroom"})

public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//로그인 여부를 검사
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session  = httpRequest.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		if(loginMember == null) {
			session.setAttribute("msg", "로그인후 이용하세요");
//			httpResponse.sendRedirect(httpRequest.getHeader("Referer"));
			String referrer = httpRequest.getHeader("Referrer");
			System.out.println("referrer : " + referrer);
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/");
			return;
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
