import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LoginUI extends JFrame {

	private JTextField user;
	private JPasswordField password;

	public LoginUI() {
		super("��¼����");
		user = new JTextField(5);
		password = new JPasswordField(5);
		password.setEchoChar('*');

		JPanel p1 = new JPanel();
		p1.add(new JLabel("�˺�:"));
		p1.add(user);
		p1.add(new JLabel("����:"));
		p1.add(password);

		add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel();
		JButton button = new JButton("��¼");
		button.addActionListener(new ActionListener()// ��¼������
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (user.getText().equals(ConnectDb.search("���"))
						&& password.getText().equals(ConnectDb.search("����"))) {

					JOptionPane.showMessageDialog(LoginUI.this,
							"Login Success!");
					new ClientUI();
					LoginUI.this.dispose();

				} else {

					JOptionPane.showMessageDialog(LoginUI.this, "Login Fail!");
				}
			}
		});
		p2.add(button);

		button = new JButton("ȡ��");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		p2.add(button);
		add(p2, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocation(100, 200);
	}

	public static void main(String[] args) {
		new LoginUI();
	}

}
