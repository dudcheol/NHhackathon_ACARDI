package com.nh.saerok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Member;
import com.nh.saerok.service.MemberService;

@RestController
//@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberService service;

	@GetMapping(value="/member")
	public List<Member> selectAll(){
		return service.selectAll();
	}
	
	@GetMapping(value="/member/{id}")
	public Member selectOne(@PathVariable String id) {
		return service.selectOne(id);
	}
	
	@PostMapping(value="/member")
	public Map create(Member m) {
		service.create(m);
		Map<String, String> map = new HashMap();
		map.put("result", "추가성공");
		return map;
	}
	
	// 삭제 -> status를 업데이트
	@PutMapping(value="/member/delete")
	public Map delete(String id) {
		service.delete(id);
		
		Map<String, String> map = new HashMap();
		map.put("result", "탈퇴성공");
		return map;
	}
	
	@PutMapping(value="/member")
	public Map update(Member m) {
		service.update(m);
		
		Map<String, String> map = new HashMap();
		map.put("result", "수정성공");
		return map;
	}
}
