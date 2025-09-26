package com.example.kok.mapper;

import com.example.kok.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void insertMember(MemberVO memberVO);
}
