package com.nh.saerok.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nh.saerok.dto.Member;
import com.nh.saerok.mapper.MemberMapper;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	MemberMapper mapper;

	@Override
	public List<Member> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public Member selectOne(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public int create(Member m) {
		return mapper.create(m);
	}

	@Override
	public int delete(String id) {
		return mapper.delete(id);
	}

	@Override
	public int update(Member m) {
		return mapper.update(m);
	}
	
	@Override
	public int login(String id) {
		int x = mapper.login(id);
		return x;
	}
}
