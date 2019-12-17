package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Dept;
import bean.Staff;

public class DeptDAO extends BaseDAO {

	public DeptDAO() {
		super();
	}

	public Dept getDept(int id) {
		String query = "select * from " + Dept.TABLE_NAME + " where " + Dept.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			Dept dept = new Dept();
			if (rs.next()) {
				dept.setDeptId(rs.getInt(Dept.ID));
				dept.setDeptName(rs.getString(Dept.NAME)); 
				dept.setCreatedTime(rs.getTimestamp(Dept.CREATED_TIME));
			}
			preparedStatement.close();
			return dept;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<Dept> getAllDepts() {
		System.out.print(">> DeptDAO: "); // FIXME
		String query = "select " + Dept.ID + " from " + Dept.TABLE_NAME;
		try {
			preparedStatement = connection.prepareStatement(query);
			System.out.print("> Chạy truy vấn: " + preparedStatement.toString()); // FIXME
			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<Dept> deptList = new ArrayList<Dept>();
			while (rs.next()) {
				deptList.add(this.getDept(rs.getInt(1)));
			}
			preparedStatement.close();
			System.out.print("> Đã lấy danh sách phòng ban. "); // FIXME
			return deptList;
		} catch (SQLException e) {
			System.out.print("> Lỗi " + e + " > NULL "); // FIXME
			return null;
		}
	}

	public boolean insertDept(Dept dept) {
		System.out.print(">> DeptDAO: Validating..."); // FIXME
		if (dept == null || dept.getDeptName() == null) {
			System.out.print("Failed [null]."); // FIXME
			return false;
		} else if ("".equals(dept.getDeptName().trim()) || Dept.MAXLENGTH_NAME < dept.getDeptName().length()) {
			System.out.print("Failed [empty]."); // FIXME
			return false;
		} else if (this.isExist(dept)) {
			System.out.print("Failed [existed before]."); // FIXME
			return false;
		}
		System.out.print("OK!\nInserting... "); // FIXME
		String query = "insert into " + Dept.TABLE_NAME + "(" + Dept.NAME + ") values(?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dept.getDeptName());
			System.out.print(preparedStatement.toString()); // FIXME
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.print("Failed [sql exception]."); // FIXME
			return false;
		}

	}

	private boolean isExist(Dept dept) {
		String query = "select * from " + Dept.TABLE_NAME + " where " + Dept.NAME + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, dept.getDeptName().trim());
			return preparedStatement.executeQuery().next();
		} catch (SQLException e) {
			return true;
		}
	}

	public boolean deleteDept(int id) {
		System.out.print(">> DeptDAO: Validating..."); // FIXME
		if (id <= 0) {
			System.out.print("Failed [negative id]."); // FIXME
			return false;
		} else if (!this.isFreeToDelete(id)) {
			System.out.print("Failed [not free to delete]."); // FIXME
			return false;
		}
		System.out.print("OK!\nDeleting... "); // FIXME
		String query = "delete from " + Dept.TABLE_NAME + " where " + Dept.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			System.out.print(preparedStatement.toString()); // FIXME
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.print("Failed [sql exception]."); // FIXME
			return false;
		}
	}

	private boolean isFreeToDelete(int id) {
		String query = "select * from " + Staff.TABLE_NAME + " where " + Dept.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			System.out.print(preparedStatement.toString()); // FIXME
			return !(preparedStatement.executeQuery().next());
		} catch (SQLException e) {
			System.out.print("Failed [sql exception]."); // FIXME
			return false;
		}
	}

	public ArrayList<Dept> getDeptByKeyword(String searchQuery) {
		String query = "select " + Dept.ID + " from " + Dept.TABLE_NAME + " where " + Dept.NAME + " like ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + searchQuery + "%");
			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<Dept> deptList = new ArrayList<Dept>();
			while (rs.next()) {
				deptList.add(this.getDept(rs.getInt(1)));
			}
			return deptList;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean deleteAllDepts() {
		String selectAll = "select " + Dept.ID + " from " + Dept.TABLE_NAME;
		try {
			if (new StaffDAO().deleteAllStaffs()) {
			PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				this.deleteDept(rs.getInt(Dept.ID));
			}
			return true;
			} else {
				return false;
			}
		} catch (SQLException e) {}
		return false;
	}

}
