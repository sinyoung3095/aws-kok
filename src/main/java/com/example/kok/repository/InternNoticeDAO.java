package com.example.kok.repository;

import com.example.kok.dto.InternNoticeDTO;
import com.example.kok.mapper.InternNoticeMapper;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InternNoticeDAO {
    private final InternNoticeMapper internNoticeMapper;

//    목록 조회
    public List<InternNoticeDTO> findAll(Criteria criteria, Search search) {
        return internNoticeMapper.selectAllInternNotice(criteria, search);
    }

//    개수 조회
    public int findCountAll(){
        return internNoticeMapper.selectCountAll();
    }

//    단일 조회
    public InternNoticeDTO findById(Long id){
        return internNoticeMapper.selectById(id);
    }
//    직군 조회
    public String findJobNameByID(Long id){
        return internNoticeMapper.selectJobNameByIntId(id);
    }

//    최신 체험 공고 4개 조회
    public List<InternNoticeDTO> findLatestFour() {
        return internNoticeMapper.selectLatestFour();
    }
}
