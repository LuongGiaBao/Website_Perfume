package iuh.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import iuh.edu.dao.RegisterDao;
import iuh.edu.entity.User;
import iuh.edu.jdbc.connectDB;



/**
 * Servlet implementation class RegistrationController
 */

public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/view/client/register.jsp");
    	dispatcher.forward(request, response);
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		String created = request.getParameter("created");

		User user = new User(name, email, phone, username, password, created);

		RegisterDao register = null;
		try {
			register = new RegisterDao(connectDB.getConnect());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (register.RegisterUser(user)) // On success, you can display a message to user on Home page
		{
			request.setAttribute("Message", "Bạn đã tạo tài khoàn thành công. Mời bạn đăng nhập <a href='/WebsiteBanNuocHoa/view/client/login'>tại đây!</a>");
			RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("errMessage", "Tạo tài khoản thất bại. Hãy thử lại !!!");
			RequestDispatcher rd = request.getRequestDispatcher("/view/client/register.jsp");
			rd.forward(request, response);
		}
	}
}