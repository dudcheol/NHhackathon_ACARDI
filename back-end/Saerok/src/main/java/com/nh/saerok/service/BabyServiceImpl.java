package com.nh.saerok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.BabyDao;
import com.nh.saerok.dto.Baby;

@Service
public class BabyServiceImpl implements BabyService {

	@Autowired BabyDao babyDao;
	
	@Override
	public void addBaby(Baby baby) {
		babyDao.addBaby(baby);
	}

	@Override
	public Baby getBaby(int babyno) {
		return babyDao.getBaby(babyno);
	}

	@Override
	public String getBabyAccount(int babyno) {
		return babyDao.getBabyAccount(babyno);
	}
	
	@Override
	public List<Baby> listBaby() {
		return babyDao.listBaby();
	}

	@Override
	public void modifyBaby(Baby baby) {
		babyDao.modifyBaby(baby);
	}

	@Override
	public void deleteBaby(int babyno) {
		babyDao.deleteBaby(babyno);
	}

}
