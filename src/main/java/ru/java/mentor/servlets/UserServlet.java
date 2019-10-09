package ru.java.mentor.servlets;

import ru.java.mentor.DAOFactory.AbstractDAOFactory;
import ru.java.mentor.DAOFactory.DAO;
import ru.java.mentor.model.User;
import ru.java.mentor.service.UserService;
import ru.java.mentor.util.PropertyReader;
import ru.java.mentor.util.ReaderException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userlist")
public class UserServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String idString = request.getParameter("id");
        String edit = request.getParameter("edit");
        String delete = request.getParameter("delete");
        long id;

        if (delete != null) {
            id = Long.parseLong(idString);
            User user = UserService.getInstance().getUserById(id);
            if (user != null) {
                UserService.getInstance().deleteUser(user);
            }
        }
        if (edit != null) {
            id = Long.parseLong(idString);
            User user = UserService.getInstance().getUserById(id);
            request.setAttribute("userEdit", user);
        }

        List<User> list = UserService.getInstance().getAllUsers();
        request.setAttribute("list", list);
        try {
            request.getRequestDispatcher(PropertyReader.read("userList")).forward(request, response);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        final String idSting = req.getParameter("id");
        final String name = req.getParameter("name");
        final String password = req.getParameter("password");
        final String role = req.getParameter("role");
        final String action = req.getParameter("action");

        if (requestIsValid(name, password, role)) {
            final User user = new User(name, password, role);

            if (action != null) {
                if (action.equalsIgnoreCase("add")) {
                    UserService.getInstance().addUser(user);
                }
                if (action.equalsIgnoreCase("edit")) {
                    user.setId(Long.parseLong(idSting));
                    UserService.getInstance().editUser(user);
                }
            }
        }
        doGet(req, resp);
    }

    private boolean requestIsValid(String name, String password, String role) {
        return name != null && !name.isEmpty() &&
                password != null && !password.isEmpty() &&
                role != null && !role.isEmpty();
    }
}
