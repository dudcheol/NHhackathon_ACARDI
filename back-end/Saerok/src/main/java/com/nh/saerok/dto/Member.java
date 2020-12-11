package com.nh.saerok.dto;

public class Member {
	
	private String id;
	private String password;
	private String name;
	private String phone_number;
	private String email;
	private String fin_account;
	private int status;
	
	
	
	public Member(String id, String password, String name, String phone_number, String email, String fin_account,
			int status) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone_number = phone_number;
		this.email = email;
		this.fin_account = fin_account;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFin_account() {
		return fin_account;
	}
	public void setFin_account(String fin_account) {
		this.fin_account = fin_account;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	

}
