 package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.beans.customer;


public class customerDao {
	/**
	 * 用于用户没有注册过的情况，增加本条用户信息
	 * @param conn
	 * @param cus
	 * @return
	 * @throws Exception
	 */
	public int add(Connection conn,customer cus)throws Exception{
		String sql="insert into customer values(?,?,?)";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setString(1, cus.getCustomer_id());
		pstmt.setString(2, cus.getCustomer_name());
		pstmt.setString(3, cus.getCustomer_phone());
		return pstmt.executeUpdate();
	}
	/**
	 * 弃用
	 * @param conn
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Connection conn, String id) throws SQLException {
		String sql="delete from customer where customer_id=?";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	/**
	 * query用来确定用户是否注册过，注册过且信息正确返回2，注册过但信息不对返回1，没有注册过返回0
	 * @param conn
	 * @param cus
	 * @return
	 * @throws Exception
	 */
	public int query(Connection conn,customer cus) throws Exception {
		String sql="select * from customer where customer_id=?";
		PreparedStatement pstmt  = conn.prepareStatement(sql);
		pstmt.setString(1, cus.getCustomer_id());
		ResultSet rs =  pstmt.executeQuery();
		if(rs.next()) {
			String  sql1="select * from customer where customer_id=? and customer_name=? and customer_phone=?";
			PreparedStatement pstmt1  = conn.prepareStatement(sql1);
			pstmt1.setString(1, cus.getCustomer_id());
			pstmt1.setString(2, cus.getCustomer_name());
			pstmt1.setString(3, cus.getCustomer_phone());
			ResultSet rs1 =  pstmt1.executeQuery();
			if(rs1.next()) {
				return 2;
			}else {
				return 1;
			}
		}else {
			return 0;
		}
	}
}
