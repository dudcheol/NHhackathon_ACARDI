package com.nh.saerok.dao;

import java.util.List;

import com.nh.saerok.dto.Member;

public interface MemberDao {
	public List<Member> selectAll();
	public Member selectOne(String id);
	public int create(Member m);
	
	// 삭제 -> status를 업데이트
	public int delete(String id);
	public int update(Member m);
	
	public Member login(String id);
}
