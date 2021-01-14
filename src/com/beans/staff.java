package com.beans;

public class staff {
	private int staff_number;
	private String staff_name;
	public int getStaff_number() {
		return staff_number;
	}
	public void setStaff_number(int staff_number) {
		this.staff_number = staff_number;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	@Override
	public String toString() {
		return "staff [staff_number=" + staff_number + ", staff_name=" + staff_name + "]";
	}
	public staff(int staff_number, String staff_name) {
		super();
		this.staff_number = staff_number;
		this.staff_name = staff_name;
	}
	public staff() {
		super();
	}
}
