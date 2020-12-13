package com.nh.saerok.dto;

public class Family {
	
	private String member_id;
	
	private int baby_no;
	
	private int no;
	
	private String relation;
	
	public Family() {}
	
	public Family(String member_id, int baby_no, int no, String relation) {
		this.member_id = member_id;
		this.baby_no = baby_no;
		this.no = no;
		this.relation = relation;
	}
	
	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getBaby_no() {
		return baby_no;
	}

	public void setBaby_no(int baby_no) {
		this.baby_no = baby_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
}
