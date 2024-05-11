package controll;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String username = request.getParameter("username");
         String password = request.getParameter("password");
         String name = request.getParameter("name");
         String email = request.getParameter("email");
         String phone = request.getParameter("phone");
         String address = request.getParameter("address");

         // Kiểm tra xem các trường có được cung cấp hay không
         if (username != null && password != null && name != null && email != null && phone != null && address != null) {
             // Kết nối tới cơ sở dữ liệu
             Connection conn = null;
             PreparedStatement stmt = null;
             try {
                 Class.forName("com.mysql.jdbc.Driver");
                 String dbURL = "jdbc:mysql://localhost:3306/favorite";
                 String dbUsername = "root";
                 String dbPassword = "3112";
                 conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

                 // Kiểm tra xem tên đăng nhập đã tồn tại trong cơ sở dữ liệu hay chưa
                 String checkUsernameQuery = "SELECT COUNT(*) FROM user WHERE username = ?;";
                 stmt = conn.prepareStatement(checkUsernameQuery);
                 stmt.setString(1, username);
                 ResultSet resultSet = stmt.executeQuery();
                 resultSet.next();
                 int count = resultSet.getInt(1);
                 if (count > 0) {
                     response.getWriter().println("<p class=\"error-message\">Tên đăng nhập đã được sử dụng. Vui lòng chọn tên đăng nhập khác.</p>");
                 } else {
                     String insertUserQuery = "INSERT INTO user (`username`, `password`, `name`, `email`, `phone`, `address`, `createDate`) "
                     		+ "VALUES (?, ?, ?, ?, ?, ?, NOW())";
                     stmt = conn.prepareStatement(insertUserQuery);
                     stmt.setString(1, username);
                     stmt.setString(2, password);
                     stmt.setString(3, name);
                     stmt.setString(4, email);
                     stmt.setString(5, phone);
                     stmt.setString(6, address);
                     int rowsAffected = stmt.executeUpdate();
                     if (rowsAffected > 0) {
                         HttpSession session = request.getSession();
                         session.setAttribute("username", username);
                         session.setAttribute("isLoggedIn", true);
                         response.sendRedirect("Home");
                     } else {
                         response.getWriter().println("<p class=\"error-message\">Đăng ký không thành công. Vui lòng thử lại sau.</p>");
                     }
                 }
             } catch (ClassNotFoundException | SQLException e) {
                 e.printStackTrace();
                 response.getWriter().println("<p class=\"error-message\">Đăng ký không thành công. Vui lòng thử lại sau.</p>");
             } finally {
                 // Đóng kết nối và tài nguyên
                 try {
                     if (stmt != null) {
                         stmt.close();
                     }
                     if (conn != null) {
                         conn.close();
                     }
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
     }
}