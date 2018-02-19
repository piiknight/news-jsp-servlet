package util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;

public class AuthUtil {
	public static boolean CheckLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userLogin");
		
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return false;
		}
		return true;
	}
}
