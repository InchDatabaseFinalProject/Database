package com.beans;

public class out_record_add {
	private int in_number;
	private int out_number;
	public int getIn_number() {
		return in_number;
	}
	public void setIn_number(int in_number) {
		this.in_number = in_number;
	}
	public int getOut_number() {
		return out_number;
	}
	public void setOut_number(int out_number) {
		this.out_number = out_number;
	}
	@Override
	public String toString() {
		return "out_record_add [in_number=" + in_number + ", out_number=" + out_number + "]";
	}
	public out_record_add(int in_number, int out_number) {
		super();
		this.in_number = in_number;
		this.out_number = out_number;
	}
	public out_record_add() {
		super();
	}
}