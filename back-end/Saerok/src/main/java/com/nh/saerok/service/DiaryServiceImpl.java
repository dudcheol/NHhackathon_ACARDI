package com.nh.saerok.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.DiaryDao;
import com.nh.saerok.dto.Diary;
import com.nh.saerok.dto.Photo;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	DiaryDao dao;
	
	@Override
	public List<Diary> selectAll(String baby_no) {
		return dao.selectAll(baby_no);
	}

	@Override
	public Diary selectOne(String baby_no, String num) {
		return dao.selectOne(baby_no, num);
	}

	@Override // 게시판 등록
	public int insert(Diary diary) {
		return dao.insert(diary);
	}

	@Override
	public int delete(String num) {
		return dao.delete(num);
	}

	@Override
	public int update(Diary diary) {
		return dao.update(diary);
	}

	@Override
	public int savePhoto(Photo photo) {
		return dao.savePhoto(photo);
	}

	@Override
	public String maxId() {
		return dao.maxId();
	}

	@Override
	public List<Diary> selectByDate(String baby_no, String year, String month) {
		return dao.selectByDate(baby_no, year, month);
	}

	@Override
	public List<Photo> selectPhotos(String diary_no) {
		return dao.selectPhotos(diary_no);
	}

	@Override
	public int updatePhoto(Photo photo) {
		return dao.updatePhoto(photo);
	}

}