package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.room;

public class roomDao {
	/**
	 * add为向room表中增加记录，但是似乎没有用到
	 * @param conn
	 * @param room
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,room room)throws Exception{
		String sql="insert into room values(?,?,?,?)";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setInt(1, room.getRoom_number());
		pstmt.setString(2, room.getRoom_type());
		pstmt.setFloat(3, room.getDay_price());
		pstmt.setString(4, room.getStaff_name());
		return pstmt.executeUpdate();
	}
	/**
	 * list为inFrm提供显示现有的房间相关信息
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con)throws Exception{
		String sql="select * from room";
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		return pstmt.executeQuery();
	}
	/**
	 * price提供价格，以供out_record罚款以及in_record_add费用的计算
	 * @param conn
	 * @param room_number
	 * @return
	 * @throws Exception
	 */
	public float price(Connection conn,int room_number) throws Exception {
		String sql="select * from room where room_number=?";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setInt(1, room_number);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getFloat(3);
		}else {
			return 0;
		}
	}
}
