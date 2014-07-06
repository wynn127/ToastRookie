package com.spring.board;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeObj {
	private String email;
	private String modifiedTime;
	private String registeredTime;
	
	public TimeObj(){};
	public TimeObj(String email, String rTime, String mTime){
		this.email = email;
		this.modifiedTime = mTime;
		this.registeredTime = rTime;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		Pattern p = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(email);
		if(!m.matches()){
			//
			System.out.println("올바른 이메일이 아닙니다");
		}
		else{
			this.email = email;
		}
	}
	public String getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(String mTime){
		this.modifiedTime = mTime;
	}
	public String getRegisteredTime(){
		return registeredTime;
	}
	public void setRegisteredTime(String rTime){
		this.registeredTime = rTime;
	}
}
