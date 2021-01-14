package com.beans;

public class room {

	private int room_number;
	private String room_type;
	private float day_price;
	private String staff_name;

	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public float getDay_price() {
		return day_price;
	}
	public void setDay_price(float day_price) {
		this.day_price = day_price;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	@Override
	public String toString() {
		return "room [room_number=" + room_number + ", room_type=" + room_type + ", day_price=" + day_price
				+ ", staff_name=" + staff_name + "]";
	}
	public room(int room_number, String room_type, float day_price, String staff_name) {
		super();
		this.room_number = room_number;
		this.room_type = room_type;
		this.day_price = day_price;
		this.staff_name = staff_name;
	}
	public room() {
		super();
	}
}