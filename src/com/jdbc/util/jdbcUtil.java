package com.jdbc.util;

import java.sql.*;

import org.junit.jupiter.api.Test;

public class jdbcUtil {
    //对于使用jdbc创建与数据库的连接需要先建立连接
    public  static Connection getConnection() throws Exception {
        String user="root";
        String password="sa";
        String url="jdbc:mysql://127.0.0.1:3306/db_hotel?characterEncoding=utf-8&serverTimezone=UTC";
        String DriverClass="com.mysql.cj.jdbc.Driver";
        //3、使用反射，载入驱动
        Class.forName(DriverClass);
        //4、建立连接
        Connection connection= DriverManager.getConnection(url,user,password);
        return connection;
    }

    //使用完连接后必须释放资源，增删改的释放资源
    public  static  void closeResource(Connection conn, Statement ps){
        //释放连接以及statement
        try {
            if(conn!=null)
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(ps!=null)
            ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    //释放资源，为查询的释放资源
    public  static  void closeResource(Connection conn, Statement ps ,ResultSet resultSet){
        //释放连接、statement以及结果集
        try {
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(ps!=null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Test
    public void test1() {
    	jdbcUtil jbu=new jdbcUtil(); 
    	Connection conn=null;
    	try {
			 conn=jbu.getConnection();
			 //System.out.print("aaaa");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.print("bbbbb");
		}finally {
			jbu.closeResource(conn, null);
		}
    }
}
