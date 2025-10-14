package test_pages;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig1 {

	Properties pro;
	public ReadConfig1() {
		File src=new File("./config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public String url() {
		String url=pro.getProperty("baseurl");
		return url;
	}
}
