package com.nh.saerok.dto;

public class Diary {
	
	private String no;
	private String title;
	private String content;
	private String registered_at;
	private String cost;
	private String baby_no;
	private String member_id;
	
	public Diary() {}
	
	public Diary(String no, String title, String content, String registered_at, String cost, String baby_no,
			String member_id) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.registered_at = registered_at;
		this.cost = cost;
		this.baby_no = baby_no;
		this.member_id = member_id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegistered_at() {
		return registered_at;
	}

	public void setRegistered_at(String registered_at) {
		this.registered_at = registered_at;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(String baby_no) {
		this.baby_no = baby_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
}
