package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.in_record;

public class in_recordDao {
	/**
	 * add为in_record表增加记录，注意此处不用给定入住编号，插入数据库后由数据库生成！
	 * @param con
	 * @param ire
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,in_record ire)throws Exception{
		String sql="insert into in_record(room_number,customer_id,in_date,last_date,admin_number) values(?,?,?,?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1, ire.getRoom_number());
		pstmt.setString(2, ire.getCustomer_id());
		pstmt.setDate(3, ire.getIn_date());
		pstmt.setDate(4, ire.getLast_date());
		pstmt.setString(5, ire.getAdmin_number());
		return pstmt.executeUpdate();
	}
	/**
	 * query用来确认inFrm界面中用房时间是否与其他人冲突
	 * @param conn
	 * @param inDate
	 * @param outDate
	 * @param room_number
	 * @return
	 * @throws Exception
	 */
	public boolean query(Connection conn,Date inDate,Date outDate,int room_number)throws Exception {
		String sql="select * from in_record where room_number=? and ((in_date<=? and last_date>=?) or (in_date<=? and last_date>=?))";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setInt(1, room_number);
		pstmt.setDate(2, outDate);
		pstmt.setDate(3, outDate);
		pstmt.setDate(4, inDate);		
		pstmt.setDate(5, inDate);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return false;
		}
		return true;
	}
	/**
	 * 下面的query用在outFrm界面，用来标识现在还没有办理退房的房间
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public ResultSet query(Connection conn)throws Exception{
		String sql="select in_number,room_number,customer_id,last_date from in_record where in_number not in(select distinct in_number from out_record_add)";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		return pstmt.executeQuery();
	}
}
