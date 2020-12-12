package com.nh.saerok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Family;
import com.nh.saerok.service.FamilyService;

@CrossOrigin
@RestController
public class FamilyController {
	
	@Autowired
	FamilyService familyservice;

	@GetMapping(value="/family")
	public List<Family> selectAllFamily(){
		return familyservice.selectAllFamily();
	}
	
	@GetMapping(value="/family/{no}")
	public Family selectOne(@PathVariable int no) {
		return familyservice.selectOneFamily(no);
	}
	
	@PostMapping(value="/family")
	public Map create(@RequestBody Family f) {
		familyservice.createFamily(f);
		Map<String, String> map = new HashMap();
		map.put("result", "추가성공");
		return map;
	}
	
//	// 삭제 -> status를 업데이트
//	@PutMapping(value="/members/delete")
//	public Map delete(String id) {
//		service.delete(id);
//		
//		Map<String, String> map = new HashMap();
//		map.put("result", "탈퇴성공");
//		return map;
//	}
	
	@PutMapping(value="/family")
	public Map updateFamily(@RequestBody Family f) {
		familyservice.updateFamily(f);
		
		Map<String, String> map = new HashMap();
		map.put("result", "수정성공");
		return map;
	}
}
