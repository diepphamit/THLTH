/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Position;

/**
 * @author NguyenBaAnh
 *
 */
public class PositionDAO extends BaseDAO {

	/**
	 * 
	 */
	public PositionDAO() {
		super();
	}

	public Position getPosition(int id) {
		String query = "select * from " + Position.TABLE_NAME + " where " + Position.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			Position pos = new Position();
			if (rs.next()) {
				pos.setPositionId(rs.getInt(Position.ID));
				pos.setPositionName(rs.getString(Position.NAME));
			}
			preparedStatement.close();
			return pos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Position> getAllPositions() {
		System.out.print(">> PositionDAO: "); // FIXME
		String query = "select " + Position.ID + " from " + Position.TABLE_NAME;
		try {
			preparedStatement = connection.prepareStatement(query);
			System.out.print("> Chạy truy vấn: " + preparedStatement.toString()); // FIXME
			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<Position> posList = new ArrayList<Position>();
			while (rs.next()) {
				posList.add(this.getPosition(rs.getInt(1)));
			}
			preparedStatement.close();
			System.out.print("> Đã lấy danh sách chức vụ "); // FIXME
			return posList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("> Lỗi: " + e + " > NULL "); // FIXME
			return null;
		}
	}

}
