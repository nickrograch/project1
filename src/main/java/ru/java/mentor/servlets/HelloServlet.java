package ru.java.mentor.servlets;

import ru.java.mentor.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    private final String page = "/WEB-INF/view/hello.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("loginedUser");
        req.setAttribute("loginedUser", loginedUser);
        req.getRequestDispatcher(page).forward(req, resp);
    }

}
