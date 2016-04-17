package net.xorsat.model;

public class Branch {
	private String branch_name;
	private String branch_address;
	private String branch_phone;
	private double latitude;
	private double longitude;

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBranch_address() {
		return branch_address;
	}

	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}

	public String getBranch_phone() {
		return branch_phone;
	}

	public void setBranch_phone(String branch_phone) {
		this.branch_phone = branch_phone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
