package controll;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UserDao;
import entity.User;

@WebServlet("/customerInfo")
public class CustomerInfoServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDao.getUser(userId);
        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("customerInfo.jsp");
        dispatcher.forward(request, response);
    }

    public void destroy() {
        // Giải phóng tài nguyên (nếu cần)
    	
    }
}
