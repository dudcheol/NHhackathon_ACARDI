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
	
	public String getMemberId() {
		return member_id;
	}

	public void setMemberId(String member_id) {
		this.member_id = member_id;
	}

	public int getBabyNo() {
		return baby_no;
	}

	public void setBabyNo(int baby_no) {
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
