package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.bean.News;
import model.dao.CategoryDAO;
import model.dao.NewsDAO;
import util.AuthUtil;

@MultipartConfig
public class AdminEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	private NewsDAO newsDAO;

	public AdminEditNewsController() {
		super();
		categoryDAO = new CategoryDAO();
		newsDAO = new NewsDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
			return;
		}
		
		News news = newsDAO.getItem(id);
		if (news == null) {
			response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
			return;
		}
		
		ArrayList<Category> categories = categoryDAO.getItems();
		if (categories.size() == 0) {
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		}
		
		request.setAttribute("news", news);
		request.setAttribute("categories", categories);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/news/edit.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!AuthUtil.CheckLogin(request, response)) {
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		
		int catId = 0;
		try {
			catId = Integer.parseInt(request.getParameter("categories"));
		} catch (NumberFormatException e) {
			// Trả về id của cat không hợp lệ
			response.sendRedirect(request.getContextPath() + "/admin/news/edit?error=1");
			return;
		}
		
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");
		
		// validate
		if (name.isEmpty() || preview.isEmpty() || detail.isEmpty() ) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/news/edit.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
			return;
		}
		
		// upload ảnh
		final Part part = request.getPart("picture");
		String fileName = getFileName(part);
		String dirPath = request.getServletContext().getRealPath("/files");
		
		String filePath = "";
		if (!"".equals(fileName)) {
				
			// tạo đường dẫn thư mục chứa file

			File dirFile = new File(dirPath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}

			String temp = part.getContentType();
			System.out.println(temp);
			String[] arr = temp.split("/");
			boolean check = false;
			if ("image".equals(arr[0])) {
				check = true;
			}
			if (check) {
				// Có sửa hình ảnh
				if (!"".equals(newsDAO.getItem(id).getPicture())) {
					// Xóa hình ảnh cũ, xóa file
					String urlFileDel = dirPath + File.separator + newsDAO.getItem(id).getPicture();
					File delFilr = new File(urlFileDel);
					if (delFilr.exists()) {
						delFilr.delete();
					}
				}
				
				long time = System.currentTimeMillis();
				fileName = "files" + "_" + Long.toString(time) + "." + arr[arr.length - 1];
				
				filePath = dirPath + File.separator + fileName;
				System.out.println(filePath);
				part.write(filePath);
				
			} else {
				News news = newsDAO.getItem(id);
				if (news == null) {
					response.sendRedirect(request.getContextPath() + "/admin/news?error=1");
					return;
				}
				
				ArrayList<Category> categories = categoryDAO.getItems();
				if (categories.size() == 0) {
					response.sendRedirect(request.getContextPath() + "/admin/news");
					return;
				}
				
				request.setAttribute("news", news);
				request.setAttribute("categories", categories);

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/news/edit.jsp?err=1");
				rd.forward(request, response);
				return;
			}
		} else {
			fileName = newsDAO.getItem(id).getPicture();
		}
		
		News item = new News(id, name, preview, detail, null, new Category(catId, null), fileName) ;
		if (newsDAO.editItem(item) > 0) {
			//part.write(filePath);
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news?err=3");
			return;
		}
		
		
	}

	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    for (String content : partHeader.split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
}
