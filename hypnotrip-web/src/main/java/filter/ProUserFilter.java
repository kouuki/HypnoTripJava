package filter;

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

import com.esprit.hypnotrip.presentation.mbeans.LoginBean;

@WebFilter("/pages/proUserHome/*")
public class ProUserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
		if (loginBean == null) {
			response.sendRedirect("http://localhost:18080/hypnotrip-web/Home.jsf");
		}
		if (loginBean != null && loginBean.getLoggedInAsProUser()) {
			filterChain.doFilter(request, response);
		} else {
			if (loginBean.getLoggedInAsSimpleUser()) {
				response.sendRedirect("http://localhost:18080/hypnotrip-web/pages/simpleUserHome/home.jsf");
			} else
				response.sendRedirect("http://localhost:18080/hypnotrip-web/pages/Admin/AdminHome.jsf");

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
