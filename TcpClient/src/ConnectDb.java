import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDb {

	public static Connection creatConn() {
		Connection conn = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");// 加载驱动程序
			conn = DriverManager.getConnection("jdbc:odbc:client", "", ""); // 建立連接，创建Connect对象
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
			Statement sta = creatConn().createStatement(); // 创建语句
			ResultSet resu = sta.executeQuery(request); // 执行查询语句
			if (resu.next()) {
				infor = resu.getString(tag);// 得到“密碼”对应字段
				System.out.println(infor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infor;
	}

}
