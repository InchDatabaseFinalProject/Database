package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.admin;

public class adminDao {
	/**
	 * login为loginFrm提供登录支持
	 * @param con
	 * @param ad
	 * @return
	 * @throws Exception
	 */
	public  admin login(Connection con,admin ad)throws Exception{
		admin resultUser=null;
		String sql="select * from admin where admin_number=? and admin_password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, ad.getAdmin_number());
		pstmt.setString(2, ad.getAdmin_password());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			resultUser=new admin();
			resultUser.setAdmin_number(rs.getString("admin_number"));
			resultUser.setAdmin_name(rs.getString("admin_name"));
			resultUser.setAdmin_password(rs.getString("admin_password"));
		}
		//System.out.print(resultUser);
		return resultUser;
	}
}
