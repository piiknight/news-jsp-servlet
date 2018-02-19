package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.UserDAO;
import util.AuthUtil;

public class AdminDelUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    public AdminDelUserController() {
        super();
        userDAO = new UserDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/users?err=2");
		}
		
		HttpSession session = request.getSession();
		User userLogin = (User) session.getAttribute("userLogin");
		
		if ("admin".equals(userDAO.getItem(id).getUsername())) {
			// Không cho phép xóa
			response.sendRedirect(request.getContextPath() + "/admin/users?err=4");
			return;
		} else {
			if ("admin".equals(userLogin.getUsername())) {
				if (userDAO.delItem(id) > 0	) {
					// xóa thành công
					response.sendRedirect(request.getContextPath() + "/admin/users?msg=2");
					return;
				} else {
					// xóa thất bại
					response.sendRedirect(request.getContextPath() + "/admin/users?err=3");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/users?error=2");
				return;
			}

			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
