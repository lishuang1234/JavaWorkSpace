package com.txy.client;

import java.util.Scanner;

public class ClientControl {
	private static TcpClient client;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (!checkDb())
			;//死循环等待账号及密码输入正确退出
		client = new TcpClient();//連接
	}

	private static boolean checkDb() {//检查身份数据库
		// TODO Auto-generated method stub
		System.out.println("请输入账号：");
		Scanner sa = new Scanner(System.in);
		String zhangHao = sa.nextLine();
		if (CheckDb.search("账号", zhangHao)) {
			System.out.println("请输入密码：");
			String miMa = sa.nextLine();
			if (CheckDb.search("密码", miMa)) {
				return true;
			} else
				System.out.println("密码错误！");
			return false;
		} else {
			System.out.println("账号不存在！");
			return false;
		}
	}
}
