package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.out_record;

public class out_recordDao {
	/**
	 * add为out_record表增加退房信息
	 * @param con
	 * @param ore
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,out_record ore)throws Exception{
		String sql="insert into out_record(room_number,customer_id,out_time,fine,admin_number) values(?,?,?,?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1, ore.getRoom_number());
		pstmt.setString(2, ore.getCustomer_id());
		pstmt.setDate(3, ore.getOut_time());
		pstmt.setFloat(4, ore.getFine());
		pstmt.setString(5, ore.getAdmin_number());
		return pstmt.executeUpdate();
	}
	/**
	 * query表为查询当前的退房编号，在outFrm中使用，是为了提供给out_record_add表退房编号数据
	 * @param conn
	 * @param ore
	 * @return
	 * @throws Exception
	 */
	public int query(Connection conn,out_record ore) throws Exception {
		String sql="select * from out_record where room_number=? and customer_id =? and out_time=? and fine=? and admin_number=?";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setInt(1, ore.getRoom_number());
		pstmt.setString(2, ore.getCustomer_id());
		pstmt.setDate(3, ore.getOut_time());
		pstmt.setFloat(4, ore.getFine());
		pstmt.setString(5, ore.getAdmin_number());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			return rs.getInt(1);
		}else {
			return 0;
		}
	}
}
