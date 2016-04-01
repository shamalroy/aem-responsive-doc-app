package me.tostring.aemdoc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shamalroy on 4/1/16.
 */

public class UrlFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) request).getRequestURI().toString();
            System.out.println(uri);
            ((HttpServletResponse) response).sendRedirect("/?d=" + uri);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    public void destroy() {

    }
}
