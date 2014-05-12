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
		super("µÇÂ¼½çÃæ");
		user = new JTextField(5);
		password = new JPasswordField(5);
		password.setEchoChar('*');

		JPanel p1 = new JPanel();
		p1.add(new JLabel("ÕËºÅ:"));
		p1.add(user);
		p1.add(new JLabel("ÃÜÂë:"));
		p1.add(password);

		add(p1, BorderLayout.CENTER);

		JPanel p2 = new JPanel();
		JButton button = new JButton("µÇÂ¼");
		button.addActionListener(new ActionListener()// µÇÂ¼¼àÌýÆ÷
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (user.getText().equals(ConnectDb.search("±àºÅ"))
						&& password.getText().equals(ConnectDb.search("ÃÜÂë"))) {

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

		button = new JButton("È¡Ïû");
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
