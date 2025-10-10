package com.example.kok.mapper;

import com.example.kok.dto.RequestInternDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestInternMapper {
//    인턴지원 멤버 아이디로 조회
    public List<RequestInternDTO> selectRequestInternById(Long id);
//    인턴 지원 내역 조회
    public List<RequestInternDTO> selectRequestInternByUserId(Long id);

}
