package web.filters;

import model.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CustomerAuthorizationFilter", urlPatterns = {"/customerdashboard"})
public class CustomerAuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        User user = (User) ((HttpServletRequest)request).getSession().getAttribute("user");

        if (user != null){
            if (user.getUsertype().equals("CUSTOMER")) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect(
                        ((HttpServletRequest)request).getContextPath() + "/error"
                );
            }
        } else {
            ((HttpServletResponse) response).sendRedirect(
                    ((HttpServletRequest)request).getContextPath() + "/login"
            );
        }



    }
}
