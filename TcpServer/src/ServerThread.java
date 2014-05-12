import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread extends Thread {
	/**
	 * ��������
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
			print("�����������ɹ�!" + "�˿ںţ�" + port);
			print("�ȴ����ӿͻ��ˣ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("����������ʧ��!");
		}
		try {
			client = server.accept();
			print("���ӳɹ�!\n" + client.toString());
			ui.btnSend.setEnabled(true);
			new ReceiverThread(ui, client).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			print("����ʧ��!");
		}

	}

	/**
	 * ��ʾ��Ϣ
	 * 
	 * @param infor
	 */
	private void print(String infor) {
		// TODO Auto-generated method stub

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
		ui.taShow.setText(ui.taShow.getText() + df.format(new Date()) + ""
				+ "\n");
		ui.taShow.setText(ui.taShow.getText() + infor + "\n");

	}

	/**
	 * ������Ϣ
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
	 * �رշ�������
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
			print("�رշ�����ʧ�ܣ�");
		}
		print("�رշ�������");
	}

}
