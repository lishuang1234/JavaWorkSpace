package com.txy.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.txy.server.EncryInformation;

public class TcpClient extends Thread {
	private Socket socket;
	private int port = 10000;
	private InetAddress addr;
	public InputStream input;
	public OutputStream output;
	private HoldFile file;

	public TcpClient() {
		port = 10000;
		file = new HoldFile();
		try {
			addr = InetAddress.getByName("localhost");
			socket = new Socket(addr, port);
			// 连接到本地服务端
			System.out.println("客户端连接成功！");
			output = socket.getOutputStream();
			input = socket.getInputStream();
			this.start();
			sendMsg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		String msg = "";
		byte[] b = new byte[2048];
		while (true) {
			try {
				int length = input.read(b);
				// msg = EncryInformation.encry(new String(b, 0, length));
				// EncryInformation.encry(new String(b, 0, length));
				msg = new String(
						EncryInformation.encry(new String(b, 0, length)));
				if (msg.equals("byebye")) {
					break;
					// guanbi
				}

				System.out.println("收到Server的消息：" + msg + "\n");
				file.writeFile("收到Server的消息：" + msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMsg() {
		while (true) {
			try {
				// TODO Auto-generated method stub
				// System.out.println("请输入要发送的消息：");
				Scanner scanner = new Scanner(System.in);
				String str = scanner.nextLine();
				if (str.equals("bye")) {
					break;
				}
				System.out.println("client:" + str);
				output.write(EncryInformation.encry( str));
				file.writeFile("client:" + str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
