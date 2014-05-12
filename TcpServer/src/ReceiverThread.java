import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiverThread extends Thread {
	/**
	 * 接收消息线程
	 */
	public Socket client;
	public ServerUI ui;
	public MyFile file;
	public boolean flag;
	public InputStream input;

	public ReceiverThread() {
	};

	public ReceiverThread(ServerUI ui, Socket client) {
		this.client = client;
		this.ui = ui;
		file = new MyFile();
		flag = true;
		try {
			input = client.getInputStream();
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

		while (flag) {
			try {
				byte[] b = new byte[2048];
				int length = input.read(b);
				msg = InforEncry.jieMi(b, length);
				print("<007-->" + msg);
				if (msg != null)
					file.writeFile("<007-->" + msg);
				if (msg.equals("byebye")) {
					closeReceiver();
					flag = false;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				closeReceiver();
				break;
			}
		}
	}

	private void print(String msg) {
		// TODO Auto-generated method stub

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		ui.taShow.setText(ui.taShow.getText() + df.format(new Date()) + ""
				+ "\n");
		ui.taShow.setText(ui.taShow.getText() + msg + "\n");
	}

	public void closeReceiver() {
		flag = false;
		try {
			if (client != null)
				client.close();
			if (input != null)
				input.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("停止接收消息失败");
		}
		print("停止接收消息！");
	}
}
