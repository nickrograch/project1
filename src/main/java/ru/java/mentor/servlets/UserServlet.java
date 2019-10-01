package ru.java.mentor.servlets;

import ru.java.mentor.DAOFactory.DAO;
import ru.java.mentor.DAOFactory.DBConnection;
import ru.java.mentor.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/userlist")
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        DAO dao = DBConnection.connectionConfiguration().getDao();
        List<User> list = dao.getAllUsers();
        req.setAttribute("list", list);
//        for (User user : list){
//            long id = user.getId();
//            String name = user.getName();
//            String surname = user.getSurname();
//            String fathername = user.getFatherName();
//            req.setAttribute("id", id);
//            req.setAttribute("name", name);
//            req.setAttribute("surname", surname);
//            req.setAttribute("fathername", fathername);
//        }
        req.getRequestDispatcher("/WEB-INF/view/userList.jsp").forward(req, resp);
    }
}
