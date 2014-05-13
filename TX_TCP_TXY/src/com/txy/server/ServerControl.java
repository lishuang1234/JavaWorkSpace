package com.txy.server;

import java.util.Scanner;

import com.txy.client.CheckDb;

public class ServerControl {
	private static TcpServer server;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (!checkDb())
			;
		server = new TcpServer();
	}

	private static boolean checkDb() {
		// TODO Auto-generated method stub
		System.out.println("ÇëÊäÈëÕËºÅ£º");
		Scanner sa = new Scanner(System.in);
		String zhangHao = sa.nextLine();
		if (CheckDb.search("ÕËºÅ", zhangHao)) {
			System.out.println("ÇëÊäÈëÃÜÂë£º");
			String miMa = sa.nextLine();
			if (CheckDb.search("ÃÜÂë", miMa)) {
				return true;
			} else
				System.out.println("ÃÜÂë´íÎó£¡");
			return false;
		} else {
			System.out.println("ÕËºÅ²»´æÔÚ£¡");
			return false;
		}
	}
}
