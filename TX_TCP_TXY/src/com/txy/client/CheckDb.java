package com.txy.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckDb {

	public static Connection creatConn() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// 加载驱动程序
			conn = DriverManager.getConnection("jdbc:odbc:txyClient", "", ""); // 建立連接，创建Connect对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;//返回数据库连接对象
	}

	public static boolean search(String tag, String getInfor) {
		String infor = "";
		boolean check = false;
		String request = "select" + " " + tag + " " + "from client";//构造数据库查询语句
		try {
			Statement sta = creatConn().createStatement(); // 创建语句
			ResultSet resu = sta.executeQuery(request); // 执行查询语句
			if (resu.next()) {
				infor = resu.getString(tag);// 得到“tag”对应字段
		//		System.out.println(infor);
			}
			if(infor.equals(getInfor)){//是否与输入相同
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
