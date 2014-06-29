package com.board.test;

import org.springframework.stereotype.Component;

@Component
public class TestObj{
	private String email;
	private String password;
	private String content;
	private String registerTime;
	
	public TestObj(){};
	public TestObj(String email, String password, String content, String registerTime){
		this.email = email;
		this.password = password;
		this.content = content;
		this.registerTime = registerTime;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getRegisterTime(){
		return registerTime;
	}
	public void setRegisterTime(String regTime){
		this.registerTime = regTime;
	}
}