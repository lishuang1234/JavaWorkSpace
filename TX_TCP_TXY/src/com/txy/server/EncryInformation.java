package com.txy.server;

public class EncryInformation {

	public static byte[] encry(String infor) {

		byte[] a = infor.getBytes();
		for (int i = 0; i < a.length; i++) {
			a[i] = (byte) ~a[i];
		}
		return a;
	}
}
