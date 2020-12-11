package com.nh.saerok.dao;

import java.util.List;

import com.nh.saerok.dto.Family;

public interface FamilyDao {
	public List<Family> selectAllFamily();
	public Family selectOneFamily(int no);
	public int createFamily(Family f);
	
	// 삭제 -> status를 업데이트
//	public int deleteFamily(String id);
	public int updateFamily(Family f);
}
