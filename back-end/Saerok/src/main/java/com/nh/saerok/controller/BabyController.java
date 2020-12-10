package com.nh.saerok.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Baby;
import com.nh.saerok.service.BabyService;

@MapperScan(basePackages = {"com.nh.saerok.mapper"})
@CrossOrigin
@RestController
@RequestMapping("/baby")
public class BabyController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private BabyService babyService;
	
	@PostMapping
	public ResponseEntity<String> addBaby(@RequestBody Baby baby) throws Exception {
		babyService.addBaby(baby);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Baby>> listBaby() throws Exception {
		return new ResponseEntity<List<Baby>>(babyService.listBaby(), HttpStatus.OK);
	}
	
	@GetMapping("{babyno}")
	public ResponseEntity<Baby> getBaby(@PathVariable("babyno") String babyno) throws Exception {
		return new ResponseEntity<Baby>(babyService.getBaby(babyno), HttpStatus.OK);
	}
	
	@PostMapping("{babyno}")
	public ResponseEntity<String> getBabyAccount(@PathVariable("babyno") String babyno) throws Exception {
		return new ResponseEntity<String>(babyService.getBabyAccount(babyno), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> modifyBaby(@RequestBody Baby baby) throws Exception {
		babyService.modifyBaby(baby);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("{babyno}")
	public ResponseEntity<String> deleteBaby(@PathVariable("babyno") String babyno) throws Exception {
		babyService.deleteBaby(babyno);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}
