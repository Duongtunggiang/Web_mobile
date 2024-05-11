package controll;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import dao.ProductDAO;
import dao.RoleDAO;
import dao.UserDao;
import entity.Product;
import entity.User;

@WebServlet("/Home")
public class HomeControll extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private UserDao userDao;
    public HomeControll() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isLoggedIn = checkLoginStatus(request);
		 
		 if (isLoggedIn) {
			String action = request.getParameter("action");
			if (action == null) {
	            action = "list1";
			}
			switch(action) {
				case "new":
	//				showNewForm(request, response);
					break;
//				case "redirect":
//	                roleBasedRedirect(request, response);
//	                break;
				case "productDetails":
				    productDetails(request, response);
				    break;
				default:
					home(request, response);
					break;
			} 
		 } else {
		        // Người dùng chưa đăng nhập, chuyển hướng đến trang đăng nhập
			 String action = request.getParameter("action");
		        if (action == null) {
		            action = "list1";
		        }
		        switch(action) {
		            case "new":
		                // Do something
		                break;
		            case "productDetails":
		                productDetails(request, response);
		                break;
		            case "customer":
		            	customer(request,response);
		            	break;
//		            case "changeAccount":
//		                changeAccount(request, response);
//		                break;
		            default:
		                home(request, response);
		                break;
		        }
		 }
	}
	private void home(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		
		ProductDAO dao = new ProductDAO();
		List<Product> list = dao.getAllProduct();
		request.setAttribute("listP", list);
		
		ProductDAO top1 = new ProductDAO();
		List<Product> top = dao.getTop1();
		request.setAttribute("Top1", top);
		///Lam tiep
		ProductDAO cate = new ProductDAO();
		List<Product> cid = cate.getProductsByCategoryId(0);
		request.setAttribute("Cate", cid);
		
		request.getRequestDispatcher("Home.jsp").forward(request, response);
		
	}
	private void customer(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    response.setContentType("text/html");
	    UserDao userDao = new UserDao(); // Tạo thể hiện của UserDao
	    int userId = Integer.parseInt(request.getParameter("userId")); // Lấy userId từ request (cần điều chỉnh tùy theo tên tham số trong request)

	    User user = userDao.getUser(userId); // Gọi phương thức getUser trên thể hiện userDao
	    List<User> userList = new ArrayList<>();
	    if (user != null) {
	        userList.add(user);
	    }
	    request.setAttribute("userList", userList);
	    
	    // Thực hiện chuyển trang trực tiếp trong servlet
	    response.sendRedirect(request.getContextPath() + "/customerInfo.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");

//        boolean isAuthenticated = authenticate(username, password);
//
//        if (isAuthenticated) {
//            HttpSession session = request.getSession();
//            session.setAttribute("username", username);
////            response.sendRedirect("Home");
//            response.sendRedirect(request.getContextPath() + "/Home");
//        } else {
////            response.sendRedirect("login.jsp?error=1");
//        	response.sendRedirect(request.getContextPath() + "/login.jsp?error=1");
//        }
        boolean authenticated = authenticate(username, password);

        if (authenticated) {
            // Lưu thông tin người dùng vào session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("isLoggedIn", true);

            // Chuyển hướng đến trang "Home.jsp"
            response.sendRedirect("Home");
        } else {
        	 request.setAttribute("loginError", "Tên người dùng hoặc mật khẩu không chính xác.");
        	    RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
        	    dispatcher.forward(request, response);
        }
	}
	

	private boolean checkLoginStatus(HttpServletRequest request) {
		 
	    HttpSession session = request.getSession();
	    String username = (String) session.getAttribute("username");
	    return username != null; 
	}
	private void roleBasedRedirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
	    String userRole = RoleDAO.getUserRole(userId);

	    if (userRole.equals("admin")) {
	        response.sendRedirect("admin.jsp");
	    } else if (userRole.equals("customer")) {
	        response.sendRedirect("Home");
	    } else {
	        // Xử lý khi vai trò không xác định
	        String errorMessage = "Vai trò của người dùng không xác định";
	        request.setAttribute("error", errorMessage);
	        request.getRequestDispatcher("error.jsp").forward(request, response);
	    }
	}
	//Chuyen sang trang san-pham voi id = &
	private void productDetails(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    response.setContentType("text/html");
	    int productId = Integer.parseInt(request.getParameter("productId"));
	    // DAO dao = new DAO();
	    List<Product> product = ProductDAO.getProductById(productId);
	    request.setAttribute("product", product);
	    request.getRequestDispatcher("san-pham.jsp").forward(request, response);
	    // response.sendRedirect(request.getContextPath() + "/Home?action=list");
	}
	private boolean authenticate(String username, String password) {
	    Connection conn = null;
	    try {
	        conn = DBContext.getConnection();

	        return true; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return false; // Trả về false nếu thông tin đăng nhập không hợp lệ
	}
}
