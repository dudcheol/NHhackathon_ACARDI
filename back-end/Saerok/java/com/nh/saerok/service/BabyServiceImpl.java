package com.nh.saerok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.BabyDao;
import com.nh.saerok.dto.Baby;
import com.nh.saerok.dto.Photo;

@Service
public class BabyServiceImpl implements BabyService {

	@Autowired BabyDao babyDao;
	
	@Override
	public int addBaby(Baby baby) {
		return babyDao.addBaby(baby);
	}

	@Override
	public Baby getBaby(String babyno) {
		return babyDao.getBaby(babyno);
	}

	@Override
	public String getBabyAccount(String babyno) {
		return babyDao.getBabyAccount(babyno);
	}
	
	@Override
	public List<Baby> listBaby(String id) {
		return babyDao.listBaby(id);
	}

	@Override
	public int modifyBaby(Baby baby) {
		return babyDao.modifyBaby(baby);
	}

	@Override
	public int deleteBaby(String babyno) {
		return babyDao.deleteBaby(babyno);
	}

	@Override
	public String maxId() {
		return babyDao.maxId();
	}

}
