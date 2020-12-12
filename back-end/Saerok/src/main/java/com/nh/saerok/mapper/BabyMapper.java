package com.nh.saerok.mapper;

import java.util.List;

import com.nh.saerok.dto.Baby;

public interface BabyMapper {

	int addBaby(Baby baby);
	Baby getBaby(String babyno);
	String getBabyAccount(String babyno);
	List<Baby> listBaby(String id);
	int modifyBaby(Baby baby);
	int deleteBaby(String babyno);
	int getBabyNoByAccount(String account);
	
}
