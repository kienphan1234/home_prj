/**
 * Copyright(C) 2018 Luvina Software Company
 * 
 * CharsetEncodingFilter.java , Aug 13, 2018 nxtung
 */
package home.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Mã hóa UTF-8
 * 
 * @author nxtung
 *
 */
public class CharsetEncodingFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
