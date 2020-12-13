package com.nh.saerok.dto;

public class Baby {
	private String no;
	private String nickname;
	private String birthday;
	private String gender;
	private String birth_time;
	private String account;
	private String profile;
	
	public Baby(String no, String nickname, String birthday, String gender, String birth_time, String account,
			String profile) {
		this.no = no;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.birth_time = birth_time;
		this.account = account;
		this.profile = profile;
	}
	
	public Baby(String no, String nickname) {
        super();
        this.no = no;
        this.nickname = nickname;
    }
	

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth_time() {
		return birth_time;
	}

	public void setBirth_time(String birth_time) {
		this.birth_time = birth_time;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
