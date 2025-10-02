package com.example.kok.mapper;

import com.example.kok.domain.MemberVO;
import com.example.kok.dto.MemberDTO;
import com.example.kok.dto.UserMemberDTO;
import com.example.kok.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    public void insertMember(MemberVO memberVO);

//    회원 조회
    public List<UserMemberDTO> selectMembers(@Param("criteria") Criteria criteria,@Param("keyword") String keyword);

//    목록 개수 조회
    public int selectCount(String keyword);

//    아이디로 회원 조회
    public Optional<UserMemberDTO> selectMember(Long memberId);
}
