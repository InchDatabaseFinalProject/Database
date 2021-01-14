package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.evaluate;
import com.jdbc.util.StringUtils;

public class evaluateDao {
	public int add(Connection con,evaluate eva)throws Exception{
		String sql="insert into evaluate values(?,?,?,?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setString(1, eva.getCustomer_name());
		pstmt.setInt(2, eva.getRoom_number());
		pstmt.setInt(3, eva.getStaff_number());
		pstmt.setDate(4, eva.getTime());
		pstmt.setString(5, eva.getEvluate());
		return pstmt.executeUpdate();
	}
	/**
	 * 评价查询
	 */
	public ResultSet select(Connection con,evaluate eva)throws Exception{
		
		StringBuffer sb=new StringBuffer("select * from evaluate ");
		if(StringUtils.isNotEmpty(eva.getCustomer_name())) {
			sb.append(" and customer_name = '"+ eva.getCustomer_name()+"'");
		}
		if((eva.getRoom_number())!=0) {
			sb.append(" and room_number = "+ eva.getRoom_number());
		}
		if((eva.getStaff_number())!=0) {
			sb.append(" and staff_number = "+ eva.getStaff_number());
		}
		if(StringUtils.isNotEmpty(eva.getEvluate())) {
			sb.append(" and Evluate = '"+ eva.getEvluate()+"'");
		}
		
		//JOptionPane.showMessageDialog(null, eva.getTime());
		if(eva.getTime()!=null) {
			sb.append(" and DATE(time)= '"+ eva.getTime()+"'");
		}
		
		PreparedStatement pstmt  = con.prepareStatement(sb.toString().replaceFirst("and", "where"));

		//JOptionPane.showMessageDialog(null, sb.toString());
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	/**
	 * 删除评价
	 */
	public int delete(Connection con,evaluate eva)throws Exception{
		String sql="delete from evaluate where customer_name=? and room_number=? and staff_number=? and time=? ";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setString(1, eva.getCustomer_name());
		pstmt.setInt(2, eva.getRoom_number());
		pstmt.setInt(3, eva.getStaff_number());
		pstmt.setDate(4, eva.getTime());
		//JOptionPane.showMessageDialog(null, sql);
		return pstmt.executeUpdate();
	}
	/**
	 * 
	 *更新评价
	 */
	public int update(Connection con,evaluate eva)throws Exception{//不可修改员工编号，若要修改，只能先删除再添加
		String sql="update  evaluate set evluate =? where  room_number=? and staff_number=? and time=? and customer_name=? ";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setString(1,eva.getEvluate() );
		pstmt.setInt(2, eva.getRoom_number());
		pstmt.setInt(3, eva.getStaff_number());
		pstmt.setDate(4, eva.getTime());
		pstmt.setString(5,eva.getCustomer_name() );
		//JOptionPane.showMessageDialog(null, sql);
		return pstmt.executeUpdate();
	}
	/**以供显示
	 * 生成相应的数据
	 * @param conn
	 * @param customer_id
	 * @param room_number
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public evaluate query(Connection conn,String customer_id,int room_number,Date time)throws Exception{
		evaluate eva=new evaluate();
		String sql1="select customer_name from customer where customer_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql1);
		pstmt.setString(1, customer_id);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			eva.setCustomer_name(rs.getString(1));
		}
		String sql2="select staff_number from room,staff where room_number=? and staff.staff_name = room.staff_name";
		pstmt = conn.prepareStatement(sql2);
		pstmt.setInt(1, room_number);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			eva.setRoom_number(room_number);
			eva.setStaff_number(rs.getInt(1));
		}
		eva.setTime(time);	
		return eva;
	}
}
