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

public class ClientUI extends JFrame {

	public JButton btnSend;
	public JButton btnStart;
	public JTextField tfSend;
	public JTextArea taShow;
	public ClientThread client;

	public ClientUI() {
		super("007�ͻ���");
		btnStart = new JButton("��������");
		btnSend = new JButton("������Ϣ");
		tfSend = new JTextField(10);
		taShow = new JTextArea();

		btnSend.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {// �������Ӱ�ť����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client = new ClientThread(ClientUI.this);
			}
		});
		btnSend.addActionListener(new ActionListener() {// ���÷�����Ϣ��ť����

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client.sendMsg(tfSend.getText());
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
					client.closeServer();
					System.exit(0); // �ر�
				}
				super.windowClosing(e);
			}
		});

		JPanel jp = new JPanel(new FlowLayout());// �½��м���������ʽ����
		// ��ӿؼ�
		jp.add(tfSend);
		jp.add(btnSend);
		jp.add(btnStart);
		jp.add(taShow);

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
