package com.nh.saerok.service;

import java.util.List;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;

public interface DiaryService {

	List<Diary> selectAll(String baby_no);
	Diary selectOne(String baby_no, String num);
	
	// 사진 업로드
	int savePhoto(Photo photo);
	
	//
	int insert(Diary diary);
	
	int delete(String num);
	int update(Diary diary);
	String maxId();
	List<Diary> selectByDate(String baby_no, String year, String month);

}
