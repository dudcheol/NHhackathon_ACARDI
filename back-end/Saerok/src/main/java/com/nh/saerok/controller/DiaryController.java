package com.nh.saerok.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Diary;
import com.nh.saerok.service.DiaryService;

@MapperScan(basePackages = {"com.nh.saerok.mapper"})
@CrossOrigin
@RestController
public class DiaryController {

	@Autowired
	DiaryService service;
	
	@GetMapping(value="/diary/{baby_no}")
	public List<Diary> selectAll(@PathVariable String baby_no) {
		System.out.println("REQUEST!! selectAll");
		List<Diary> list = null;
		try {
			list = service.selectAll(baby_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@GetMapping(value="/diary/{baby_no}/{num}")
	public Diary selectOne(@PathVariable String baby_no, @PathVariable String num) {
		Diary diary = null;
		try {
			diary = service.selectOne(baby_no, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diary;
	}
	
	@PostMapping(value="/diary")
	public int insert(@RequestBody Diary diary) {
		try {
			return service.insert(diary);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@DeleteMapping(value="/diary/{num}")
	public int delete(@PathVariable String num) {
		try {
			return service.delete(num);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@PutMapping(value="/diary")
	public int update(@RequestBody Diary diary) {
		try {
			return service.update(diary);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
