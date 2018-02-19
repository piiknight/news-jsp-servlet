package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import constant.Define;
import model.bean.Category;
import model.bean.News;
import util.DatabaseConnection;

public class NewsDAO {
	private Connection conn;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;

	public ArrayList<News> getItems() {
		ArrayList<News> listNews = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
		String sql = "SELECT news.id AS nid, news.name AS nname, preview_text, created_at, cat_id, picture, categories.id AS cid, categories.name AS cname FROM "
					+ "news INNER JOIN categories ON news.cat_id = categories.id ORDER BY news.id DESC";
		News news;
		
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				news = new News(rs.getInt("nid"), rs.getString("nname"), rs.getString("preview_text"), null, rs.getTimestamp("created_at"), new Category(rs.getInt("cid"), rs.getString("cname")), rs.getString("picture"));
				listNews.add(news);
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
		
		return listNews;
	}

	public int addItem(News item) {
		int row = 0;
		String sql = "INSERT INTO news(name, preview_text, detail_text, picture, cat_id) VALUES (?, ?, ?, ?, ?)";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview_text());
			pst.setString(3, item.getDetail_text());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getCategory().getId());
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

	public int delItem(int id) {
		int row = 0;
		String sql = "DELETE FROM news WHERE id = ?";
		
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

	public News getItem(int id) {
		News news = null;
		String sql = "SELECT news.id AS nid, news.name AS nname, preview_text, detail_text, created_at, cat_id, picture, categories.id AS cid, categories.name AS cname FROM "
				+ "news INNER JOIN categories ON news.cat_id = categories.id WHERE news.id = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if (rs.next()) {
				news = new News(rs.getInt("nid"), rs.getString("nname"), rs.getString("preview_text"), rs.getString("detail_text"), rs.getTimestamp("created_at"), new Category(rs.getInt("cid"), rs.getString("cname")), rs.getString("picture"));
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
		
		return news;
	}

	public int editItem(News item) {
		int row = 0;
		String sql = "UPDATE news SET name = ?, preview_text = ?, detail_text = ?, picture = ?, cat_id = ? WHERE id = ?";
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setString(2, item.getPreview_text());
			pst.setString(3, item.getDetail_text());
			pst.setString(4, item.getPicture());
			pst.setInt(5, item.getCategory().getId());
			pst.setInt(6, item.getId());
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
	
	public int countNews() {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumNews FROM "
				+ "news INNER JOIN categories ON news.cat_id = categories.id ";
	
		try {
			conn = DatabaseConnection.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				result = rs.getInt("sumNews");
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
	
	public int countNews(int cat_id) {
		int result = 0;
		String sql = "SELECT COUNT(*) AS sumNews FROM "
				+ "news INNER JOIN categories ON news.cat_id = categories.id WHERE cat_id = ? ";
	
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt("sumNews");
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
		return result;
		
	}
	
	public ArrayList<News> getItemsPagination(int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
		String sql = "SELECT news.id AS nid, news.name AS nname, preview_text, created_at, cat_id, picture, categories.id AS cid, categories.name AS cname FROM "
					+ "news INNER JOIN categories ON news.cat_id = categories.id ORDER BY news.id DESC LIMIT ?, ?";
		News news;
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, Define.ROW_COUNT_ADMIN_NEWS);
			rs = pst.executeQuery();
			while (rs.next()) {
				news = new News(rs.getInt("nid"), rs.getString("nname"), rs.getString("preview_text"), null, rs.getTimestamp("created_at"), new Category(rs.getInt("cid"), rs.getString("cname")), rs.getString("picture"));
				listNews.add(news);
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
		
		return listNews;
	}
	
	public ArrayList<News> getItemsPagination(int cat_id, int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
			String sql = "SELECT news.id AS nid, news.name AS nname, preview_text, created_at, cat_id, picture, categories.id AS cid, categories.name AS cname FROM "
						+ "news INNER JOIN categories ON news.cat_id = categories.id WHERE cat_id = ? ORDER BY news.id DESC LIMIT ?, ?";
		News news;
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			pst.setInt(2, offset);
			pst.setInt(3, Define.ROW_COUNT_ADMIN_NEWS);
			rs = pst.executeQuery();
			while (rs.next()) {
				news = new News(rs.getInt("nid"), rs.getString("nname"), rs.getString("preview_text"), null, rs.getTimestamp("created_at"), new Category(rs.getInt("cid"), rs.getString("cname")), rs.getString("picture"));
				listNews.add(news);
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
		
		return listNews;
	}
	
	public ArrayList<News> getItemsPaginationInvolve(int cat_id, int id, int offset) {
		ArrayList<News> listNews = new ArrayList<>();
		// id, name, preview_text, detail_text, created_at, cat_id, picture
			String sql = "SELECT news.id AS nid, news.name AS nname, preview_text, created_at, cat_id, picture, categories.id AS cid, categories.name AS cname FROM "
						+ "news INNER JOIN categories ON news.cat_id = categories.id WHERE cat_id = ? AND news.id != ? ORDER BY news.id DESC LIMIT ?, ?";
		News news;
		
		try {
			conn = DatabaseConnection.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setInt(1, cat_id);
			pst.setInt(2, id);
			pst.setInt(3, offset);
			pst.setInt(4, Define.ROW_COUNT_NEWS_INVOLVE);
			rs = pst.executeQuery();
			while (rs.next()) {
				news = new News(rs.getInt("nid"), rs.getString("nname"), rs.getString("preview_text"), null, rs.getTimestamp("created_at"), new Category(rs.getInt("cid"), rs.getString("cname")), rs.getString("picture"));
				listNews.add(news);
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
		
		return listNews;
	}
	
}
