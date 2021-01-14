package com.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

public class evaluate {
	private String customer_name ;
	private int room_number ;
	private int staff_number;
	private Date time;
	private  String evluate;
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public int getRoom_number() {
		return room_number;
	}
	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}
	public int getStaff_number() {
		return staff_number;
	}
	public void setStaff_number(int staff_number) {
		this.staff_number = staff_number;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getEvluate() {
		return evluate;
	}
	public void setEvluate(String evluate) {
		this.evluate = evluate;
	}
	@Override
	public String toString() {
		return "evaluate [customer_name=" + customer_name + ", room_number=" + room_number + ", staff_number="
				+ staff_number + ", time=" + time + ", evluate=" + evluate + "]";
	}
	public evaluate(String customer_name, int room_number, int staff_number, Date time, String evluate) {
		super(); 
		this.customer_name = customer_name;
		this.room_number = room_number;
		this.staff_number = staff_number;
		this.time = time;
		this.evluate = evluate;
	}
	public evaluate(String customer_name, int room_number, int staff_number, Date time) {
		super();
		this.customer_name = customer_name;
		this.room_number = room_number;
		this.staff_number = staff_number;
		this.time = time;
	}
	public evaluate(String customer_name, int room_number, int staff_number, String inDate) {
		super();
		this.customer_name = customer_name;
		this.room_number = room_number;
		this.staff_number = staff_number;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		java.util.Date dtemp1 = null;
		try {
			dtemp1 = formatter.parse(inDate);
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(dtemp1==null) {
			JOptionPane.showMessageDialog(null, "输入不合法，请重新输入！");
			return ;
		}
		this.time=new java.sql.Date(dtemp1.getTime());
	}
	public evaluate() {
		super();
	}
}
