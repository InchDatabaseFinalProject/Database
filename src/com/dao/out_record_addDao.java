package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.beans.out_record_add;

public class out_record_addDao {
	/**
	 * add用在out_record_add表增加入住信息，可以防止重复退房
	 * @param con
	 * @param ora
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,out_record_add ora)throws Exception{
		String sql="insert into out_record_add values(?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1, ora.getIn_number());
		pstmt.setInt(2, ora.getOut_number());
		return pstmt.executeUpdate();
	}
}
