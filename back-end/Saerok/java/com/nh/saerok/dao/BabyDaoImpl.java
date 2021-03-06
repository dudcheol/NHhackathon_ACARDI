package com.nh.saerok.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nh.saerok.dto.Baby;
import com.nh.saerok.dto.Photo;
import com.nh.saerok.mapper.BabyMapper;

@Repository
public class BabyDaoImpl implements BabyDao {

	@Autowired BabyMapper babyMapper;
	
	@Override
	public int addBaby(Baby baby) {
		if(babyMapper.addBaby(baby) == 1) {
			return babyMapper.getBabyNoByAccount(baby.getAccount());
		}
		return 0; 
	}

	@Override
	public Baby getBaby(String babyno) {
		return babyMapper.getBaby(babyno);
	}
	
	@Override
	public String getBabyAccount(String babyno) {
		return babyMapper.getBabyAccount(babyno);
	}

	@Override
	public int modifyBaby(Baby baby) {
		return babyMapper.modifyBaby(baby);
	}

	@Override
	public int deleteBaby(String babyno) {
		return babyMapper.deleteBaby(babyno);
	}

	@Override
	public List<Baby> listBaby(String id) {
		return babyMapper.listBaby(id);
	}

	@Override
	public String maxId() {
		return babyMapper.maxId();
	}


}
