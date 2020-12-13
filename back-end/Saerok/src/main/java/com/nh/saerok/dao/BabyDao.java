package com.nh.saerok.dao;

import java.util.List;

import com.nh.saerok.dto.Baby;
import com.nh.saerok.dto.Photo;

public interface BabyDao {

	int addBaby(Baby baby);
	Baby getBaby(String babyno);
	String getBabyAccount(String babyno);
	List<Baby> listBaby(String id);
	int modifyBaby(Baby baby);
	int deleteBaby(String babyno);
	String maxId();

}
