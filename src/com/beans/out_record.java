package com.beans;

import java.sql.Date;

public class out_record {
	private int out_number;
	private int room_number;
	private String customer_id;
	private Date out_time;
	private float fine;
	private String  admin_number;
	public int getOut_number() {
		return out_number;
	}
	public void setOut_number(int out_number) {
		this.out_number = out_number;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public Date getOut_time() {
		return out_time;
	}
	public void setOut_time(Date out_time) {
		this.out_time = out_time;
	}
	public float getFine() {
		return fine;
	}
	public void setFine(float fine) {
		this.fine = fine;
	}
	public String getAdmin_number() {
		return admin_number;
	}
	public void setAdmin_number(String admin_number) {
		this.admin_number = admin_number;
	}
	@Override
	public String toString() {
		return "out_record [out_number=" + out_number + ", room_number=" + room_number + ", customer_id=" + customer_id
				+ ", out_time=" + out_time + ", fine=" + fine + ", admin_number=" + admin_number + "]";
	}
	public out_record(int room_number, String customer_id, Date out_time, float fine,
			String admin_number) {
		super();
		this.room_number = room_number;
		this.customer_id = customer_id;
		this.out_time = out_time;
		this.fine = fine;
		this.admin_number = admin_number;
	}
	public out_record() {
		super();
	}
}