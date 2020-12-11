package com.nh.saerok.dto;

public class Photo {
	
	private String no;
	private String save_path;
	private String upload_name;
	private String save_name;
	private String diary_no;
	
	public Photo(String no, String save_path, String upload_name, String save_name, String diary_no) {
		this.no = no;
		this.save_path = save_path;
		this.upload_name = upload_name;
		this.save_name = save_name;
		this.diary_no = diary_no;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSave_path() {
		return save_path;
	}

	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}

	public String getUpload_name() {
		return upload_name;
	}

	public void setUpload_name(String upload_name) {
		this.upload_name = upload_name;
	}

	public String getSave_name() {
		return save_name;
	}

	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}

	public String getDiary_no() {
		return diary_no;
	}

	public void setDiary_no(String diary_no) {
		this.diary_no = diary_no;
	}
	
}
