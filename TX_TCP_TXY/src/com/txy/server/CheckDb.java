package com.txy.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckDb {

	public static Connection creatConn() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// ������������
			conn = DriverManager.getConnection("jdbc:odbc:txyServer", "", ""); // �����B�ӣ�����Connect����
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
			Statement sta = creatConn().createStatement(); // �������
			ResultSet resu = sta.executeQuery(request); // ִ�в�ѯ���
			if (resu.next()) {
				infor = resu.getString(tag);// �õ����ܴa����Ӧ�ֶ�
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
