package com.txy.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer extends Thread {
	private ServerSocket server;
	private int port = 10000;
	public OutputStream output;
	private Socket client;
	private InputStream input;
	private HoldFile file;

	public TcpServer() {
		this.start();
		file = new HoldFile();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client = server.accept();
			System.out.println("客户端连接成功" + client.toString());
			output = client.getOutputStream();
			input = client.getInputStream();
			new Receiver(input).start();
			sendMsg();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

				System.out.println("server:" + str);
				output.write(EncryInformation.encry("server:" + str));
				file.writeFile( str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	class Receiver extends Thread {
		private InputStream input;

		public Receiver(InputStream input) {
			this.input = input;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			byte[] b = new byte[1024];
			String msg = "";
			while (true) {
				try {
					int length = input.read(b);
				//	msg = EncryInformation.encry(new String(b, 0, length));
		//			EncryInformation.encry(new String(b, 0, length));
					msg = new String(	EncryInformation.encry(new String(b, 0, length)));
					System.out.println("收到Client的消息：" + msg + "\n");
					file.writeFile("收到Client的消息：" + msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
