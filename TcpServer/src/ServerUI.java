import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerUI extends JFrame {

	public JButton btnSend;
	public JButton btnStart;
	public JTextField tfSend;
	public JTextArea taShow;
	public ServerThread server;

	public ServerUI() {
		super("008�����");
		btnStart = new JButton("��������");
		btnSend = new JButton("������Ϣ");

		tfSend = new JTextField(10);
		taShow = new JTextArea();

		btnSend.setEnabled(false);

		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				server = new ServerThread(ServerUI.this);
				btnStart.setEnabled(false);
			}
		});
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stud
				server.sendMsg(tfSend.getText() + "");
				tfSend.setText("");
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

				int a = JOptionPane.showConfirmDialog(null, "ȷ���ر���", "��ܰ��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (a == 1) {
					server.closeServer();
					System.exit(0); // �ر�
				}

				super.windowClosing(e);
			}
		});

		JPanel jp = new JPanel(new FlowLayout());// �½��м���������ʽ����
		// ��ӿؼ�
		jp.add(taShow);
		jp.add(tfSend);
		jp.add(btnSend);
		jp.add(btnStart);

		this.add(jp, BorderLayout.SOUTH);
		JScrollPane js = new JScrollPane(taShow);// ���ù�����
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// ����Ҫ��ʱ�����
		this.taShow.setEditable(false);// �ı��򲻿ɱ༭
		this.add(js, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ڹرղ���
		this.setSize(400, 300);
		this.setLocation(100, 200);
		this.setResizable(true);// ���ô��ڴ�С�ɱ�
		this.setVisible(true);// ������ʾ
	}
}
