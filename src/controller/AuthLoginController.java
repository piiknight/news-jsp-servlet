package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.StringLibrary;

public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public AuthLoginController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userLogin");
		
		if (user != null) {
			response.sendRedirect(request.getContextPath() + "/admin/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/auth/login.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = StringLibrary.md5(request.getParameter("password"));
		
		User user = userDAO.checkLogin(username, password);
		
		if (user != null) {
			session.setAttribute("userLogin", user);
			response.sendRedirect(request.getContextPath() + "/admin/index");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/auth/login?err=1");
			return;
		}
		
	}

}
