/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import bean.Dept;
import bean.Position;
import bean.Staff;

/**
 * @author NguyenBaAnh
 *
 */
public class StaffDAO extends BaseDAO {

	/**
	 * 
	 */
	public StaffDAO() {
		super();
	}

	public ArrayList<Staff> getAllStaffs() {
		String query = "select " + Staff.ID + " from " + Staff.TABLE_NAME;
		try {
			preparedStatement = connection.prepareStatement(query);
			System.out.print(">> StaffDAO: Chạy truy vấn :" + preparedStatement.toString()); // FIXME
			ResultSet rs = preparedStatement.executeQuery();
			ArrayList<Staff> staffList = new ArrayList<Staff>();
			while (rs.next()) {
				staffList.add(this.getStaff(rs.getString(1)));
			}
			preparedStatement.close();
			return staffList;
		} catch (SQLException e) {
			System.err.print("Lỗi: " + e + " > NULL"); // FIXME
			e.printStackTrace();
			return null;
		}
	}

	public Staff getStaff(String id) {
		if (id == null || "".equals(id.trim())) {
			return null;
		}
		String query = "select * from " + Staff.TABLE_NAME + " where " + Staff.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			Staff staff = new Staff();
			if (rs.next()) {
				staff.setId(rs.getString(Staff.ID));
				staff.setPassword(rs.getString(Staff.PASSWORD));
				staff.setName(rs.getString(Staff.NAME));
				staff.setAddress(rs.getString(Staff.ADDRESS));
				staff.setPhone(rs.getString(Staff.PHONE));
				staff.setDept(new DeptDAO().getDept(rs.getInt(Dept.ID)));
				staff.setPosition(new PositionDAO().getPosition(rs.getInt(Position.ID)));
			}
			preparedStatement.close();
			return staff;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean insertStaff(Staff staff) {
		System.out.print(">> StaffDAO: kiểm tra hợp lệ dữ liệu..."); // FIXME
		// Kiểm tra sự hợp lệ của thông tin
		if (staff == null || "".equals(staff.getName().trim()) || "".equals(staff.getPassword().trim())
				|| Staff.MAXLENGH_ID < staff.getId().length() || Staff.MAXLENGTH_PASSWORD < staff.getPassword().length()
				|| Staff.MAXLENGTH_PHONE < staff.getPhone().length()
				|| Staff.MAXLENGTH_ADDRESS < staff.getAddress().length() || staff.getId().length() < 3
				|| staff.getDept() == null || staff.getPosition() == null) {
			System.out.print("...! ");
			return false;
		}
		System.out.print("OK "); // FIXME
		// Cập nhật CSDL
		String query = "insert into " + Staff.TABLE_NAME + "(" + Staff.ID + "," + Staff.PASSWORD + "," + Staff.NAME
				+ "," + Staff.ADDRESS + "," + Staff.PHONE + "," + Dept.ID + "," + Position.ID
				+ ") values(?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, staff.getId());
			preparedStatement.setString(2, staff.getPassword());
			preparedStatement.setString(3, staff.getName());
			preparedStatement.setString(4, staff.getAddress());
			preparedStatement.setString(5, staff.getPhone());
			preparedStatement.setInt(6, staff.getDept().getDeptId());
			preparedStatement.setInt(7, staff.getPosition().getPositionId());
			System.out.print("> Chạy truy vấn: " + preparedStatement.toString() + "..."); // FIXME
			boolean added = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			return added;
		} catch (SQLException e) {
			System.err.print("> Lỗi: " + e); // FIXME
			return false;
		}
	}

	public boolean deleteStaff(String id) {
		System.out.println(">> StaffDAO: validting... ");
		if (id == null || id.trim().equals("") || id.length() > Staff.MAXLENGH_ID) {
			return false;
		}
		System.out.print("OK!\nDeleting... "); // FIXME
		String query = "delete from " + Staff.TABLE_NAME + " where " + Staff.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			System.out.print(preparedStatement.toString()); // FIXME
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.print("Failed [sql exception]."); // FIXME
			return false;
		}
	}

	public ArrayList<Staff> getStaffByKeyword(String searchQuery) {
		String query = "select " + Staff.ID + " from " + Staff.TABLE_NAME + " where " + Staff.ID + " like ? or "
				+ Staff.NAME + " like ? or " + Staff.ADDRESS + " like ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + searchQuery + "%");
			preparedStatement.setString(2, "%" + searchQuery + "%");
			preparedStatement.setString(3, "%" + searchQuery + "%");
			System.out.print(preparedStatement.toString()); // FIXME
			ArrayList<Staff> staffList = new ArrayList<Staff>();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				staffList.add(this.getStaff(rs.getString(Staff.ID)));
			}
			return staffList;
		} catch (SQLException e) {
			System.out.print("Failed [sql exception]."); // FIXME
			return null;
		}
	}

	public boolean updateStaff(Staff staff) {
		System.out.print(">> StaffDAO: kiểm tra hợp lệ dữ liệu..."); // FIXME
		// Kiểm tra sự hợp lệ của thông tin
		if (staff == null || "".equals(staff.getName().trim()) || Staff.MAXLENGH_ID < staff.getId().length()
				|| Staff.MAXLENGTH_PHONE < staff.getPhone().length()
				|| Staff.MAXLENGTH_ADDRESS < staff.getAddress().length() || staff.getDept() == null
				|| staff.getPosition() == null) {
			System.out.print("...! ");
			return false;
		}
		System.out.print("OK "); // FIXME
		// Cập nhật CSDL
		String query = "update " + Staff.TABLE_NAME + " set " + Staff.NAME + "=?, " + Staff.ADDRESS + "=?, "
				+ Staff.PHONE + "=?, " + Dept.ID + "=?, " + Position.ID + "=? " + " where " + Staff.ID + "=? ";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, staff.getName());
			preparedStatement.setString(2, staff.getAddress());
			preparedStatement.setString(3, staff.getPhone());
			preparedStatement.setInt(4, staff.getDept().getDeptId());
			preparedStatement.setInt(5, staff.getPosition().getPositionId());
			preparedStatement.setString(6, staff.getId());
			System.out.print("> Chạy truy vấn: " + preparedStatement.toString() + "..."); // FIXME
			boolean added = preparedStatement.executeUpdate() > 0 ? true : false;
			preparedStatement.close();
			return added;
		} catch (SQLException e) {
			System.err.print("> Lỗi: " + e); // FIXME
			return false;
		}
	}

	public ArrayList<Staff> getStaffByDeptId(int id) {
		String query = "select " + Staff.ID + " from " + Staff.TABLE_NAME + " where " + Dept.ID + "=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ArrayList<Staff> staffList = new ArrayList<Staff>();
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				staffList.add(this.getStaff(rs.getString(Staff.ID)));
			}
			return staffList;
		} catch (SQLException e) {
			System.out.print("Lỗi: " + e); // FIXME
			return null;
		}
	}

	public boolean isExist(Staff staff) {
		if (staff == null || staff.getId() == null || staff.getPassword() == null
				|| staff.getPassword().trim().equals("") || staff.getId().trim().equals("")) {
			return false;
		} else {
			String checkQuery = "select * from " + Staff.TABLE_NAME + " where " + Staff.ID + "=? and " + Staff.PASSWORD
					+ "=?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
				preparedStatement.setString(1, staff.getId());
				preparedStatement.setString(2, staff.getPassword());
				return preparedStatement.executeQuery().next();
			} catch (SQLException e) {
				System.out.print("Lỗi: " + e); // FIXME
				return false;
			}
		}

	}

	public boolean changePassword(String userId, String oldPassword, String newPassword) {
		String updateQuery = "update " + Staff.TABLE_NAME 
				+ " set " + Staff.PASSWORD + "=? where " + Staff.ID + "=? and " + Staff.PASSWORD + "=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, userId);
			preparedStatement.setString(3, oldPassword);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			System.out.print("Lỗi: " + e); // FIXME
			return false;
		}
	}

	public boolean deleteAllStaffs() {
		String selectAll = "select " + Staff.ID + " from " + Staff.TABLE_NAME 
				+ " where " + Staff.ID + " <> 'admin'";
		String resetAdmin = "update " + Staff.TABLE_NAME 
				+ " set " + Staff.PASSWORD + " ='admin' where " + Staff.ID + "='admin'";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(selectAll);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				this.deleteStaff(rs.getString(Staff.ID));
			}
			preparedStatement = connection.prepareStatement(resetAdmin);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {}
		return false;
	}

}
