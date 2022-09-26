package myboard.constants;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class MyboardConstants {

	public static Properties queries;
	
	static {
		queries = new Properties();
		try {
			File file = new File("C:/eclipse-workspace/MyBoard/src/main/webapp/WEB-INF/props/query.properties");
			Reader reader = new FileReader(file);
			queries.load(reader);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	} // static 초기화 블록
	
} // class
