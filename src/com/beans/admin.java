package com.beans;

public class admin {
	private String admin_number;
	private String admin_name;
	private String admin_password;
	public admin() {
	}
	public String getAdmin_number() {
		return admin_number;
	}
	public void setAdmin_number(String admin_number) {
		this.admin_number = admin_number;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	@Override
	public String toString() {
		return "admin [admin_number=" + admin_number + ", admin_name=" + admin_name + ", admin_password="
				+ admin_password + "]";
	}
	public admin(String admin_number, String admin_name, String admin_password) {
		super();
		this.admin_number = admin_number;
		this.admin_name = admin_name;
		this.admin_password = admin_password;
	}
	public admin(String admin_number, String admin_password) {
		super();
		this.admin_number = admin_number;
		this.admin_password = admin_password;
	}
	

}
