package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import model.bean.Category;
import model.bean.User;
import util.DatabaseConnection;

public class UserDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ArrayList<User> getItems(){
		ArrayList<User> items = new ArrayList<>();
		String sql = "SELECT id, username, password, fullname FROM users";
		
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
				items.add(user);
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
		
		return items;
	}

	public int addItem(User user) {
		int row = 0;
		String sql = "INSERT INTO users(username, password, fullname) VALUES (?, ?, ?)";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFullname());
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null && pst != null) {
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

	public User getItemByUsername(String username) {
		User user = null;
		String sql = "SELECT id, username, fullname FROM users WHERE username = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
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
		
		return user;
	}

	public int delItem(int id) {
		int row = 0;
		String sql = "DELETE FROM users WHERE id = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null && pst != null) {
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

	public User getItem(int id) {
		User user = null;
		String sql = "SELECT id, username, password, fullname FROM users WHERE id = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
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
		
		return user;

	}

	public int editItem(User user) {
		int row = 0;
		String sql = "UPDATE users SET password = ? , fullname = ? WHERE id = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getFullname());
			pst.setInt(3, user.getId());
			row = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			if (conn != null && pst != null) {
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

	public User checkLogin(String username, String password) {
		String sql = "SELECT id, fullname FROM users WHERE username = ? AND password = ?";
		User user = null;
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String fullname = rs.getString("fullname");
				user = new User(id, username, fullname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		finally {
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
		return user;
	}
	
	public int countUser() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumUsers FROM users ";
	
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sumUsers");
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
	
	public ArrayList<User> getItemsPagination(int offset) {
		ArrayList<User> users = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
		String sql = "SELECT id, username, fullname FROM users ORDER BY id DESC LIMIT ?, ?";
		User user;
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN_USER);
			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String fullname = rs.getString("fullname"); 
				user = new User(id, username, fullname);
				users.add(user);
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
		
		return users;
	}
	
}
