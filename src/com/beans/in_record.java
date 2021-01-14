package com.beans;

import java.sql.Date;

public class in_record {
	private int in_number;
	private int room_number;
	private String customer_id;
	private Date in_date;
	private Date last_date;
	private String admin_number;
	public int getIn_number() {
		return in_number;
	}
	public void setIn_number(int in_number) {
		this.in_number = in_number;
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
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public Date getLast_date() {
		return last_date;
	}
	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}
	public String getAdmin_number() {
		return admin_number;
	}
	public void setAdmin_number(String admin_number) {
		this.admin_number = admin_number;
	}
	@Override
	public String toString() {
		return "in_record [in_number=" + in_number + ", room_number=" + room_number + ", customer_id=" + customer_id
				+ ", in_date=" + in_date + ", last_date=" + last_date + ", admin_number=" + admin_number + "]";
	}
	public in_record( int room_number, String customer_id, Date in_date, Date last_date,
			String i) {
		super();
		this.room_number = room_number;
		this.customer_id = customer_id;
		this.in_date = in_date;
		this.last_date = last_date;
		this.admin_number = i;
	}
	public in_record() {
		super();
	}
}