package filters;

import utils.MyConstant;
import utils.auth.Auth;
import utils.auth.AuthImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Auth auth = new AuthImpl((HttpServletRequest) servletRequest);
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        if (auth.isLogin()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            res.sendRedirect("./register");
        }
    }

    @Override
    public void destroy() {

    }
}
