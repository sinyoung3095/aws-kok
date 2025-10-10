package com.example.kok.mapper;

import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.RequestInternDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequestInternMapper {
//    인턴지원 멤버 아이디로 조회
    public List<RequestInternDTO> selectRequestInternById(Long id);

    //    지원서 넣기
    public void insertRequest(RequestInternDTO requestInternDTO);

//    멤버별 지원서 조회
    public List<RequestInternDTO> selectRequestById(Long id);

}
