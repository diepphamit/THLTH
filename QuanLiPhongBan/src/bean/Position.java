/**
 * 
 */
package bean;


public class Position {
	public static final String TABLE_NAME = "chuc_vu";
	public static final String ID = "ma_chuc_vu", NAME = "ten_chuc_vu";
	public static final int MAXLENGTH_NAME = 100;
	int positionId;
	String positionName;
	public Position() {}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	

}
