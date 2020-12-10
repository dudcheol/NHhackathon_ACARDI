package com.nh.saerok.service;

import java.util.List;

import com.nh.saerok.dto.Baby;

public interface BabyService {
	void addBaby(Baby baby);
	Baby getBaby(int babyno);
	String getBabyAccount(int babyno);
	List<Baby> listBaby();
	void modifyBaby(Baby baby);
	void deleteBaby(int babyno);
}
