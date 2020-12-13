package com.nh.saerok.dto;

public class Photo {
	
	private String no;
	private String save_path; // 저장 위치
	private String upload_name;  // 원래 file name
	private String save_name; // 저장된 file name => 1) 년월일시분초 이렇게 하면 파일이름이 겹칠 일 없음 2) uuid 사용
	private String diary_no;
	
	public Photo() {}
	
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
