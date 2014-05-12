import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */

/**
 * @author LS
 * 
 */
public class MyFile {
	public String path = "D:/server.txt";
	public File file = new File(path);
	public FileOutputStream out;

	public MyFile() {
		creatFile();
	}

	public void creatFile() {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public synchronized void writeFile(String msg) {
		String filein = msg + "\r\n";
        
		try {
			out = new FileOutputStream(file, true);
			out.write(filein.getBytes());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
