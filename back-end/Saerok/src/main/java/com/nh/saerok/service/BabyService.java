package com.nh.saerok.service;

import java.util.List;

import com.nh.saerok.dto.Baby;

public interface BabyService {
	void addBaby(Baby baby);
	Baby getBaby(String babyno);
	String getBabyAccount(String babyno);
	List<Baby> listBaby();
	void modifyBaby(Baby baby);
	void deleteBaby(String babyno);
}
