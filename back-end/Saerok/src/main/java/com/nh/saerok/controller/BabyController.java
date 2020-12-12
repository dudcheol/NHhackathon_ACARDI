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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nh.saerok.dto.Baby;
import com.nh.saerok.service.BabyService;

@MapperScan(basePackages = { "com.nh.saerok.mapper" })
@CrossOrigin
@RestController
@RequestMapping("/baby")
public class BabyController {

	@Autowired
	private BabyService babyService;

	@PostMapping
	public int addBaby(@RequestBody Baby baby) throws Exception {
		try {
			return babyService.addBaby(baby);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@GetMapping("/list/{id}")
	public List<Baby> listBaby(@PathVariable("id") String id) {
		List<Baby> list = null;
		try {
			list = babyService.listBaby(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@GetMapping("{babyno}")
	public Baby getBaby(@PathVariable("babyno") String babyno) throws Exception {
		Baby baby = null;
		try {
			baby = babyService.getBaby(babyno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baby;
	}

	@PostMapping("{babyno}")
	public Baby getBabyAccount(@PathVariable("babyno") String babyno) throws Exception {
		Baby baby = null;
		try {
			babyService.getBabyAccount(babyno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baby;
	}

	@PutMapping
	public int modifyBaby(@RequestBody Baby baby) throws Exception {
		try {
			return babyService.modifyBaby(baby);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@DeleteMapping("{babyno}")
	public int deleteBaby(@PathVariable("babyno") String babyno) throws Exception {
		try {
			return babyService.deleteBaby(babyno);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
