package com.example.kok.mapper;

import com.example.kok.dto.AdminExperienceDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminExperienceMapper {
//    전체(목록)
    public List<AdminExperienceDTO> selectAdminExperienceAll(@Param("criteria") Criteria criteria, @Param("search") Search search);

//    검색 개수
    public int selectAdminExperienceSearchCountAll(@Param("search") Search search);

//    전체 개수
    public int selectAdminExperienceCountAll();

//    상세
    public AdminExperienceDTO selectAdminExperienceById(Long id);
}
