package com.nh.saerok.service;

import java.util.List;

import com.nh.saerok.dto.Baby;

public interface BabyService {
	int addBaby(Baby baby);
	Baby getBaby(String babyno);
	String getBabyAccount(String babyno);
	List<Baby> listBaby(String id);
	int modifyBaby(Baby baby);
	int deleteBaby(String babyno);
}
