package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beans.staff;
import com.jdbc.util.StringUtils;

public class staffDao {
	/**
	 * add为增加员工表的数据
	 * @param con
	 * @param sta
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,staff sta)throws Exception{
		String sql="insert into staff values(?,?)";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1, sta.getStaff_number());
		pstmt.setString(2, sta.getStaff_name());
		return pstmt.executeUpdate();
	}
	/**
	 * 员工情况查询
	 * @param con
	 * @param sta
	 * @return
	 * @throws Exception
	 */
	public ResultSet select(Connection con,staff sta)throws Exception{
		StringBuffer sb=new StringBuffer("select * from staff ");
		if((sta.getStaff_number())!=0) {
			sb.append("and Staff_number = "+ sta.getStaff_number());
		}
		if(StringUtils.isNotEmpty(sta.getStaff_name())) {
			sb.append("and Staff_name = '"+ sta.getStaff_name()+"'");
		}
		PreparedStatement pstmt  = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	/**
	 * 删除员工
	 */
	public int delete(Connection con,int staff_number)throws Exception{
		String sql="delete from staff where staff_number=?";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setInt(1,staff_number);
		return pstmt.executeUpdate();
	}
	/**
	 *更新员工
	 */
	public int update(Connection con,staff sta)throws Exception{//不可修改员工编号，若要修改，只能先删除再添加
		String sql="update  staff set staff_name=? where staff_number=?";
		PreparedStatement pstmt  = con.prepareStatement(sql);
		pstmt.setString(1, sta.getStaff_name());
		pstmt.setInt(2, sta.getStaff_number());
		return pstmt.executeUpdate();
	}
}
