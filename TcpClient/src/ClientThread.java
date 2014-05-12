import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClientThread extends Thread {
	public ClientUI ui;
	public Socket client;
	public String add;
	public final int port = 10001;
	public InputStream input;
	public OutputStream output;
	public InetAddress addr;
	public List<String> note;
	public MyFile file;

	public ClientThread(ClientUI ui) {
		// TODO Auto-generated constructor stub
		this.ui = ui;
		file = new MyFile();
		try {
			addr = InetAddress.getByName("localhost");
			client = new Socket(addr, port);
			print("���ӷ������ɹ���");
			print(client.toString());

			output = client.getOutputStream();
			input = client.getInputStream();

			ui.btnSend.setEnabled(true);
			ui.btnStart.setEnabled(false);
			this.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("�Ҳ�����ַ��");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("δ�򿪷������޷����ӣ�");
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
				msg = InforEncry.jieMi(b, length);
				print("<008>--" + msg);
				if (msg != null)
					file.writeFile("<008>--" + msg);
				if (msg.equals("byebye")) {
					print("���ӶϿ���");
					closeServer();
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				print("���ӶϿ���");
				closeServer();
				break;
			}

		}
	}

	public void sendMsg(String msg) {
		// TODO Auto-generated method stub
		try {
			if (msg != null)
				file.writeFile("<007>--" + msg);
			   output.write(InforEncry.jiaMi(msg.getBytes()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		print("<007>--" + msg);
	}

	public void closeServer() {
		// TODO Auto-generated method stub
		try {
			input.close();
			output.close();
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("�ر�����ʧ�ܣ�");
		}
		print("�ر����ӣ�");
	}

	/**
	 * ��ʾ��Ϣ
	 * 
	 * @param infor
	 */
	private void print(String infor) {
		// TODO Auto-generated method stub
		// if(infor!= null)
		// file.writeFile(infor);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		ui.taShow.setText(ui.taShow.getText() + df.format(new Date()) + ""
				+ "\n");
		ui.taShow.setText(ui.taShow.getText() + infor + "\n");

	}

}
