package ru.java.mentor.util;

import ru.java.mentor.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter", urlPatterns = {"/userlist"})
public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("loginedUser");
        if (user.getRole().equals("admin")){
            chain.doFilter(req, resp);
        }
        else{
            request.getRequestDispatcher("/WEB-INF/view/accessDenied.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
