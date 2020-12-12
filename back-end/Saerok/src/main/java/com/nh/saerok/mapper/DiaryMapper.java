package com.nh.saerok.mapper;

import java.util.List;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;

public interface DiaryMapper {
	List<Diary> selectAll(String baby_no);
	
	int savePhoto(Photo photo);
	
	Diary selectOne(String baby_no, String num);

	int insert(Diary diary);

	int delete(String num);

	int update(Diary diary);

	String maxId();

	List<Diary> selectByDate(String baby_no, String year, String month);

	List<Photo> selectPhotos(String diary_no);
}
