import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDb {

	public static Connection creatConn() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// ������������
			conn = DriverManager.getConnection("jdbc:odbc:client", "", ""); // �����B�ӣ�����Connect����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static String search(String tag) {
		String infor = "";
		String request = "select" + " " + tag + " " + "from client";
		try {
			Statement sta = creatConn().createStatement(); // �������
			ResultSet resu = sta.executeQuery(request); // ִ�в�ѯ���
			if (resu.next()) {
				infor = resu.getString(tag);// �õ����ܴa����Ӧ�ֶ�
				System.out.println(infor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infor;
	}

}
