package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import model.bean.Category;
import util.DatabaseConnection;

public class CategoryDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> categories = new ArrayList<>();
		String sql = "SELECT id, name FROM categories ORDER BY id DESC";
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Category item = new Category(id, name);
				categories.add(item);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (rs != null && st != null && conn != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return categories;
	}
	
	public Category getItem(int id) {
		Category item = null;
		String sql = "SELECT id, name FROM categories WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Category(rs.getInt("id"), rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (rs != null && pst != null && conn != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return item;
	}

	public int addItem(Category item) {
		int row = 0;
		String sql = "INSERT INTO categories(name) VALUES (?)";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return row;
	}
	
	public int editItem(Category item) {
		int row = 0;
		String sql = "UPDATE categories SET name = ? WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getId());
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return row;
	}
	
	public int delItem(int id) {
		int row = 0;
		String sql = "DELETE FROM categories WHERE id = ?";
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (pst != null && conn != null) {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return row;
	}

	public int countCats() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumCats FROM "
				+ "categories";
	
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sumCats");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (conn != null && st != null && rs != null) {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		return result;
	}
	
	public ArrayList<Category> getItemsPagination(int offset) {
		ArrayList<Category> categories = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
		String sql = "SELECT id, name FROM categories ORDER BY id DESC LIMIT ?, ?";
		Category category;
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN_CAT);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				category = new Category(id, name);
				categories.add(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			if (conn != null && pst != null && rs != null) {
				try {
					rs.close();
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		return categories;
	}
	
}
