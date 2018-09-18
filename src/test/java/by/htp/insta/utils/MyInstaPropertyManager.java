package by.htp.insta.utils;

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
	
}