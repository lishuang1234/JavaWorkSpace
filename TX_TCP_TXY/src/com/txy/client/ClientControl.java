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
			;//��ѭ���ȴ��˺ż�����������ȷ�˳�
		client = new TcpClient();//�B��
	}

	private static boolean checkDb() {//���������ݿ�
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
