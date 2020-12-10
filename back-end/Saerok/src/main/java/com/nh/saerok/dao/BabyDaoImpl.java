package com.nh.saerok.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nh.saerok.dto.Baby;
import com.nh.saerok.mapper.BabyMapper;

@Repository
public class BabyDaoImpl implements BabyDao {

	@Autowired BabyMapper babyMapper;
	
	@Override
	public void addBaby(Baby baby) {
		babyMapper.addBaby(baby);
	}

	@Override
	public Baby getBaby(int babyno) {
		return babyMapper.getBaby(babyno);
	}
	
	@Override
	public String getBabyAccount(int babyno) {
		return babyMapper.getBabyAccount(babyno);
	}

	@Override
	public List<Baby> listBaby() {
		return babyMapper.listBaby();
	}

	@Override
	public void modifyBaby(Baby baby) {
		babyMapper.modifyBaby(baby);
	}

	@Override
	public void deleteBaby(int babyno) {
		babyMapper.deleteBaby(babyno);
	}


}
