package web.filters;

import model.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"/login","/index.jsp"})
public class AuthorizationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        User user = (User) ((HttpServletRequest)request).getSession().getAttribute("user");

        if (user != null){
            if (user.getUsertype().equals("CUSTOMER")) {
                ((HttpServletResponse) response).sendRedirect(
                        ((HttpServletRequest)request).getContextPath() + "/customerdashboard"
                );
            } else if(user.getUsertype().equals("DRIVER")) {
                ((HttpServletResponse) response).sendRedirect(
                        ((HttpServletRequest)request).getContextPath() + "/driverdashboard"
                );
            } else if (user.getUsertype().equals("ADMIN")) {
                ((HttpServletResponse) response).sendRedirect(
                        ((HttpServletRequest)request).getContextPath() + "/admindashboard"
                );
            }

        } else {
            chain.doFilter(request, response);
        }



    }
}
