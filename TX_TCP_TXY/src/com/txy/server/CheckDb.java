package com.txy.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckDb {

	public static Connection creatConn() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// 加载驱动程序
			conn = DriverManager.getConnection("jdbc:odbc:txyServer", "", ""); // 建立連接，创建Connect对象
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static boolean search(String tag, String getInfor) {
		String infor = "";
		boolean check = false;
		String request = "select" + " " + tag + " " + "from Server";
		try {
			Statement sta = creatConn().createStatement(); // 创建语句
			ResultSet resu = sta.executeQuery(request); // 执行查询语句
			if (resu.next()) {
				infor = resu.getString(tag);// 得到“密碼”对应字段
			//	System.out.println(infor);
			}
			if (infor.equals(getInfor)) {
				check = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
}
