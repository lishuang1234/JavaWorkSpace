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
		System.out.println("�������˺ţ�");
		Scanner sa = new Scanner(System.in);
		String zhangHao = sa.nextLine();
		if (CheckDb.search("�˺�", zhangHao)) {
			System.out.println("���������룺");
			String miMa = sa.nextLine();
			if (CheckDb.search("����", miMa)) {
				return true;
			} else
				System.out.println("�������");
			return false;
		} else {
			System.out.println("�˺Ų����ڣ�");
			return false;
		}
	}
}
