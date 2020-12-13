package com.nh.saerok.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;
import com.nh.saerok.mapper.DiaryMapper;

@Repository
public class DiaryDaoImpl implements DiaryDao {

	@Autowired
	DiaryMapper mapper;
	
	@Override
	public List<Diary> selectAll(String baby_no) {
		return mapper.selectAll(baby_no);
	}

	@Override
	public Diary selectOne(String baby_no, String num) {
		return mapper.selectOne(baby_no, num);
	}
	

	@Override
	public int insert(Diary diary) {
		return mapper.insert(diary);
	}

	@Override
	public int delete(String num) {
		return mapper.delete(num);
	}

	@Override
	public int update(Diary diary) {
		return mapper.update(diary);
	}

	@Override
	public int savePhoto(Photo photo) {
		return mapper.savePhoto(photo);
	}

	@Override
	public String maxId() {
		return mapper.maxId();
	}

	@Override
	public List<Diary> selectByDate(String baby_no, String year, String month) {
		return mapper.selectByDate(baby_no, year, month);
	}

	@Override
	public List<Photo> selectPhotos(String diary_no) {
		return mapper.selectPhotos(diary_no);
	}

}
