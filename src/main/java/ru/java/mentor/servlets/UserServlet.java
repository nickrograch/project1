package ru.java.mentor.servlets;

import ru.java.mentor.DAOFactory.AbstractDAOFactory;
import ru.java.mentor.DAOFactory.DAO;
import ru.java.mentor.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userlist")
public class UserServlet extends HttpServlet {
    private final String page = "/WEB-INF/view/userList.jsp";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DAO dao = AbstractDAOFactory.getDAO();
        response.setContentType("text/html");
        String idString = request.getParameter("id");
        String submit = request.getParameter("submit");
        String edit = request.getParameter("edit");
        String delete = request.getParameter("delete");
        long id;

        if (delete != null) {
            id = Long.parseLong(idString);
            User user = dao.getUserById(id);
            if (user != null) {
                dao.deleteUser(user);
            }
        }
        if (edit != null) {
            id = Long.parseLong(idString);
            User user = dao.getUserById(id);
            request.setAttribute("userEdit", user);
        }

        List<User> list = dao.getAllUsers();
        request.setAttribute("list", list);
        request.getRequestDispatcher(page).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

        final String idSting = req.getParameter("id");
        final String name = req.getParameter("name");
        final String surname = req.getParameter("surname");
        final String fathername = req.getParameter("fathername");
        final String action = req.getParameter("action");

        if (requestIsValid(name, surname, fathername)) {
            DAO dao = AbstractDAOFactory.getDAO();
            final User user = new User(name, surname, fathername);

            if (action != null) {
                if (action.equalsIgnoreCase("add")) {
                    dao.addUser(user);
                }
                if (action.equalsIgnoreCase("edit")) {
                    user.setId(Long.parseLong(idSting));
                    dao.editUser(user);
                }
            }
        }
        doGet(req, resp);
    }

    private boolean requestIsValid(String name, String surname, String fathername) {
        return name != null && !name.isEmpty() &&
                surname != null && !surname.isEmpty() &&
                fathername != null && !fathername.isEmpty();
    }
}
