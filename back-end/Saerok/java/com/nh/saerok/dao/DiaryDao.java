package com.nh.saerok.dao;

import java.util.List;

import com.nh.saerok.dto.Diary;

public interface DiaryDao {

	List<Diary> selectAll(String baby_no);

	Diary selectOne(String baby_no, String num);

	int insert(Diary diary);

	int savePhoto(Photo photo);
	
	int delete(String num);

	int update(Diary diary);
	
	List<Diary> selectByDate(String baby_no, String year, String month);
	
	String maxId();
}
