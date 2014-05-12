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
		super("007客户端");
		btnStart = new JButton("启动连接");
		btnSend = new JButton("发送消息");
		tfSend = new JTextField(10);
		taShow = new JTextArea();

		btnSend.setEnabled(false);
		btnStart.addActionListener(new ActionListener() {// 设置连接按钮监听

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				client = new ClientThread(ClientUI.this);
			}
		});
		btnSend.addActionListener(new ActionListener() {// 设置发送消息按钮监听

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

				int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
						JOptionPane.YES_NO_OPTION);
				if (a == 1) {
					client.closeServer();
					System.exit(0); // 关闭
				}
				super.windowClosing(e);
			}
		});

		JPanel jp = new JPanel(new FlowLayout());// 新建中间容器，流式布局
		// 添加控件
		jp.add(tfSend);
		jp.add(btnSend);
		jp.add(btnStart);
		jp.add(taShow);

		this.add(jp, BorderLayout.SOUTH);
		JScrollPane js = new JScrollPane(taShow);// 设置滚动条
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// 在需要的时候出现
		this.taShow.setEditable(false);// 文本域不可编辑
		this.add(js, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗口关闭操作
		this.setSize(400, 300);
		this.setLocation(100, 200);
		this.setResizable(true);// 设置窗口大小可变
		this.setVisible(true);// 设置显示
	}
}
