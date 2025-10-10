package com.example.kok.mapper;

import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.dto.InternNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InternNoticeMapper {

    public List<InternNoticeDTO> selectListById(Long userId);
}
