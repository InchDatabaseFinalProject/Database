package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.beans.in_record_add;

public class in_record_addDao {
	/**
	 * add增加in_record_add表的记录
	 * @param con
	 * @param ira
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,in_record_add ira)throws Exception{
		String sql="insert into in_record_add values(?,?,?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1, ira.getRoom_number());
		pstmt.setDate(2, ira.getIn_date());
		pstmt.setDate(3, ira.getLast_date());
		pstmt.setFloat(4, ira.getCost());
		return pstmt.executeUpdate();
		
	}
}
