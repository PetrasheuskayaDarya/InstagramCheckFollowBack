package by.htp.insta.dao.util;

import java.util.ResourceBundle;

public class MyInstaPropertyManager {
private static final ResourceBundle rb;
	
	static {
		rb = ResourceBundle.getBundle("test_config");
	}
	
	public String getUrl(){
		return rb.getString("insta.url");
	}
	
	public String getLogin(){
		return rb.getString("insta.login");
	}
	
	public String getPass(){
		return rb.getString("insta.pass");
	}
	
	
	
	public static String getDburl(){
		return rb.getString("db.dburl");
	}
	public static String getDbLogin(){
		return rb.getString("db.dblogin");
	}
	public static String getDbpass(){
		return rb.getString("db.dbpass");
	}
}