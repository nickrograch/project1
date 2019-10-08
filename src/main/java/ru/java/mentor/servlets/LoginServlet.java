package ru.java.mentor.servlets;

import ru.java.mentor.model.User;
import ru.java.mentor.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final String page = "/WEB-INF/view/login.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        final String password = req.getParameter("pass");
        User user = UserService.getInstance().findUser(name, password);
        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("loginedUser", user);
            resp.sendRedirect("/hello");
        }
        else{
            System.out.println("Wrong name or password");
        }
    }
}
