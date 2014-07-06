package com.spring.board;

import org.springframework.stereotype.Component;

@Component
public class VisitorObj extends TimeObj{
	private String email;
	private String password;
	private String content;
	
	public VisitorObj(){};
	
	public VisitorObj(String email, String password, String content){
		this.email = email;
		this.password = password;
		this.content = content;
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
}