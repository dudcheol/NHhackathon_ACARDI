package com.nh.saerok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Member;
import com.nh.saerok.service.MemberService;

@CrossOrigin
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
	public Map create(@RequestBody Member m) {
		System.out.println(m.getPhone_number());
		service.create(m);
		Map<String, String> map = new HashMap();
		map.put("result", "추가성공");
		return map;
	}
	
	// 삭제 -> status를 업데이트
	@PutMapping(value="/member/delete/{id}")
	public Map delete(@PathVariable String id) {
		service.delete(id);
		
		Map<String, String> map = new HashMap();
		map.put("result", "탈퇴성공");
		return map;
	}
	
	@PutMapping(value="/member")
	public Map update(@RequestBody Member m) {
		service.update(m);
		
		Map<String, String> map = new HashMap();
		map.put("result", "수정성공");
		return map;
	}

	@PostMapping(value="/login") // Member로 수정
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member m, HttpSession session){
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(m.getId() + " " + m.getPassword());
		try {
			Member loginUser = service.login(m.getId(), m.getPassword());
		
			if(loginUser != null) {
				resultMap.put("message", "로그인 성공");
				resultMap.put("id", loginUser.getId());
				resultMap.put("password", loginUser.getPassword());
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
