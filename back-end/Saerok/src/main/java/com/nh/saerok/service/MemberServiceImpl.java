package com.nh.saerok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.saerok.dao.MemberDao;
import com.nh.saerok.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao dao;
	
	@Override
	public List<Member> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Member selectOne(String id) {
		return dao.selectOne(id);
	}

	@Override
	public int create(Member m) {
		return dao.create(m);
	}

	@Override
	public int delete(String id) {
		return dao.delete(id);
	}

	@Override
	public int update(Member m) {
		return dao.update(m);
	}
	
	public Member login(String id) {
		return dao.login(id);
	}

}
