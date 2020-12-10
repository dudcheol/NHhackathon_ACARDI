package com.nh.saerok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.DiaryDao;
import com.nh.saerok.dto.Diary;

@Service
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	DiaryDao dao;
	
	@Override
	public List<Diary> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Diary selectOne(String baby_no, String num) {
		return dao.selectOne(baby_no, num);
	}

	@Override
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

}
