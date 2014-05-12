import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread extends Thread {
	/**
	 * 服务器端
	 */
	public ServerSocket server;
	public Socket client;
	public final int port = 10001;
	public ServerUI ui;
	public OutputStream output;
	public MyFile file;

	public ServerThread(ServerUI serverUI) {
		// TODO Auto-generated constructor stud
		this.ui = serverUI;
		this.start();
		file = new MyFile();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			server = new ServerSocket(port);
			print("启动服务器成功!" + "端口号：" + port);
			print("等待连接客户端！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("启动服务器失败!");
		}
		try {
			client = server.accept();
			print("连接成功!\n" + client.toString());
			ui.btnSend.setEnabled(true);
			new ReceiverThread(ui, client).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("连接失败!");
		}

	}

	/**
	 * 显示消息
	 * 
	 * @param infor
	 */
	private void print(String infor) {
		// TODO Auto-generated method stub

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		ui.taShow.setText(ui.taShow.getText() + df.format(new Date()) + ""
				+ "\n");
		ui.taShow.setText(ui.taShow.getText() + infor + "\n");

	}

	/**
	 * 发送消息
	 * 
	 * @param msg
	 */
	public synchronized void sendMsg(String msg) {
		try {
			if (msg != null)
		    file.writeFile("<008>--"+msg);
			output = client.getOutputStream();
			output.write(InforEncry.jiaMi(msg.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print("<008>--" + msg);
	}

	/**
	 * 关闭服务器端
	 */
	public void closeServer() {
		// TODO Auto-generated method stub
		try {
			if (output != null)
				output.close();
			if (server != null)
				server.close();
		
			new ReceiverThread().closeReceiver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("关闭服务器失败！");
		}
		print("关闭服务器！");
	}

}
