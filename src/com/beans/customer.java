package com.beans;

public class customer {
	private  String customer_id;
	private String customer_name;
	private String customer_phone;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	@Override
	public String toString() {
		return "customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_phone="
				+ customer_phone + "]";
	}
	public customer() {
		super();
	}
	public customer(String customer_id, String customer_name, String customer_phone) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
	}
}

