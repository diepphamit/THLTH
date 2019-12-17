/**
 * 
 */
package bean;


public class Staff {
	public static final String TABLE_NAME = "nhan_vien", ID = "ma_nhan_vien", PASSWORD = "mat_khau";
	public static final String NAME = "ten_nhan_vien", PHONE = "so_dien_thoai", ADDRESS = "dia_chi";
	public static final int MAXLENGH_ID = 15, MAXLENGTH_PASSWORD = 15, MAXLENGTH_PHONE = 15, MAXLENGTH_ADDRESS = 100;

	String id, name, password, phone, address;
	Dept dept;
	Position position;

	public Staff() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "[" + this.getId() + ", " + this.getName() + "]";
	}

}
