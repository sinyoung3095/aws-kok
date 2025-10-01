package com.example.kok.repository;

import com.example.kok.dto.SaveExperienceNoticeDTO;
import com.example.kok.mapper.SaveExperienceNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaveExperienceNoticeDAO {
    private final SaveExperienceNoticeMapper saveExperienceNoticeMapper;

//    공고 저장
    public void saveExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO) {
        saveExperienceNoticeMapper.insertSaveExp(saveExperienceNoticeDTO);
    }
//    공고 저장 취소
    public void deleteExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO) {
        saveExperienceNoticeMapper.deleteSaveExp(saveExperienceNoticeDTO);
    }
}
