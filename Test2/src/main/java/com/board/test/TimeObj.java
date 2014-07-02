package com.board.test;

public class TimeObj {
	private String email;
	private String modifiedTime;
	
	public TimeObj(){};
	public TimeObj(String email, String mTime){
		this.email = email;
		this.modifiedTime = mTime;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getModifiedTime(){
		return modifiedTime;
	}
	public void setModifiedTime(String mTime){
		this.modifiedTime = mTime;
	}
}
