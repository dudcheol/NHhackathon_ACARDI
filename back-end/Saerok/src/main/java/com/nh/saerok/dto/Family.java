package com.nh.saerok.dto;

public class Family {
	
	private String memberId;
	
	private int babyNo;
	
	private int no;
	
	private String relation;
	
	public Family() {}
	
	public Family(String memberId, int babyNo, int no, String relation) {
		this.memberId = memberId;
		this.babyNo = babyNo;
		this.no = no;
		this.relation = relation;
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBabyNo() {
		return babyNo;
	}

	public void setBabyNo(int babyNo) {
		this.babyNo = babyNo;
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
