package com.nh.saerok.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nh.saerok.dto.Family;
import com.nh.saerok.mapper.FamilyMapper;

@Repository
public class FamilyDaoImpl implements FamilyDao {
	
	@Autowired
	FamilyMapper familymapper;
	
	@Override
	public List<Family> selectAllFamily() {
		return familymapper.selectAllFamily();
	}

	@Override
	public Family selectOneFamily(int no) {
		return familymapper.selectOneFamily(no);
	}

	@Override
	public int createFamily(Family f) {
		return familymapper.createFamily(f);
	}

//	@Override
//	public int deleteFamily(String id) {
//		return mapper.deleteFamily(id);
//	}

	@Override
	public int updateFamily(Family f) {
		return familymapper.updateFamily(f);
	}

}
