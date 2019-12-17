/**
 * 
 */
package bean;

import java.sql.Timestamp;


public class Dept {
	public static final String TABLE_NAME = "phong_ban";
	public static final String ID = "ma_phong_ban", NAME = "ten_phong_ban";
	public static final int MAXLENGTH_NAME = 100;
	public static final String CREATED_TIME = "ngay_tao";
	int deptId;
	String deptName;
	Timestamp createdTime;
	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	int staffNumber;
	
	public int getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(int staffNumber) {
		this.staffNumber = staffNumber;
	}

	public Dept() {
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
