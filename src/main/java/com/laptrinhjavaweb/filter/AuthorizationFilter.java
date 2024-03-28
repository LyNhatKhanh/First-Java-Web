package com.laptrinhjavaweb.filter;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.SessionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

    private ServletContext context;

    // setup filter
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*
        * TODO: fix the error
        * - while access to /admin-home
        * request.getRequestURI() - return /new-jdbc/template/admin/js... ???
        * */
        String url = request.getRequestURI();
        if (url.startsWith(request.getContextPath() + "/admin")) {
            UserModel model = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
            if (model != null) {
                if (model.getRole().getCode().equals(SystemConstant.ADMIN))
                    filterChain.doFilter(servletRequest, servletResponse); // filterChain is used to invoke the next filter in the chain
                else if (model.getRole().getCode().equals(SystemConstant.USER))
                    response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_permission&alert=danger");
            } else {
                response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=not_login&alert=danger");
            }
        } else
            filterChain.doFilter(servletRequest, servletResponse); //  filterChain is used to invoke the next filter in the chain
    }

    @Override
    public void destroy() {

    }
}
