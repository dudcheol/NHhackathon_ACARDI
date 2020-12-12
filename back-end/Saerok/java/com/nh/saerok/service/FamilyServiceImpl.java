package com.nh.saerok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.FamilyDao;
import com.nh.saerok.dto.Family;

@Service
public class FamilyServiceImpl implements FamilyService{
	
	@Autowired
	FamilyDao familydao;
	
	@Override
	public List<Family> selectAllFamily() {
		return familydao.selectAllFamily();
	}

	@Override
	public Family selectOneFamily(int no) {
		return familydao.selectOneFamily(no);
	}

	@Override
	public int createFamily(Family f) {
		return familydao.createFamily(f);
	}

	@Override
	public int updateFamily(Family f) {
		return familydao.updateFamily(f);
	}


	
}
