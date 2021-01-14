package com.beans;

import java.sql.Date;

public class in_record_add {
	private  int room_number;
	private Date in_date;
	private Date last_date;
	private float cost;
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
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
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "in_record_add [room_number=" + room_number + ", in_date=" + in_date + ", last_date=" + last_date
				+ ", cost=" + cost + "]";
	}
	public in_record_add(int room_number, Date in_date, Date last_date, float cost) {
		super();
		this.room_number = room_number;
		this.in_date = in_date;
		this.last_date = last_date;
		this.cost = cost;
	}
	public in_record_add() {
		super();
	}
}
