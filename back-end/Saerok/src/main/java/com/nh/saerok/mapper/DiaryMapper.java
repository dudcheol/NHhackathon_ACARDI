package com.nh.saerok.mapper;

import java.util.List;

import com.nh.saerok.dto.Diary;

public interface DiaryMapper {

	List<Diary> selectAll(String baby_no);

	Diary selectOne(String baby_no, String num);

	int insert(Diary diary);

	int delete(String num);

	int update(Diary diary);

}
