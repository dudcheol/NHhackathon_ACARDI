package com.nh.saerok.service;

import java.util.List;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;

public interface DiaryService {

	List<Diary> selectAll(String baby_no);
	
	Diary selectOne(String baby_no, String num);
	
	int insert(Diary diary);
	
	int delete(String num);
	
	int update(Diary diary);
	
	List<Diary> selectByDate(String baby_no, String year, String month);
	
	String maxId();
	
	// 사진 업로드
	int savePhoto(Photo photo);

	List<Photo> selectPhotos(String diary_no);
	
}
