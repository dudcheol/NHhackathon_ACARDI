package com.nh.saerok.dao;

import java.util.List;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;

public interface DiaryDao {

	List<Diary> selectAll(String baby_no);

	Diary selectOne(String baby_no, String num);

	int savePhoto(Photo photo);

	int insert(Diary diary);

	int delete(String num);

	int update(Diary diary);

	String maxId();

	List<Diary> selectByDate(String baby_no, String year, String month);

	List<Photo> selectPhotos(String diary_no);
}
