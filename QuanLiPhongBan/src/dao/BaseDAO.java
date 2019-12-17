/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author NguyenBaAnh
 *
 */
public class BaseDAO {
	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	/**
	 * 
	 */
	public BaseDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String address = "jdbc:mysql://localhost:3306/ql_phongban?useUnicode=true&characterEncoding=utf-8";
			connection = DriverManager.getConnection(address,"root","");
		} catch (Exception e) {
			System.err.println("[Database constructor] Loi: " + e);
		}
	}
	/**
	 * Đóng kết nối và statement
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}

}
