package com.example.kok.service;

import com.example.kok.dto.RequestExperienceDTO;
import com.example.kok.dto.RequestExperienceFileDTO;
import com.example.kok.repository.RequestExperienceDAO;
import com.example.kok.repository.RequestExperienceFileDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestExperienceServiceImpl implements RequestExperienceService {
    private final RequestExperienceDAO requestExperienceDAO;
    private final RequestExperienceFileDAO requestExperienceFileDAO;

    @Override
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO) {
        requestExperienceDAO.applyForExperience(requestExperienceDTO);
        Long reqId=requestExperienceDTO.getId();
        Long fileId=requestExperienceDTO.getFileId();
        RequestExperienceFileDTO file=new RequestExperienceFileDTO();
        file.setFileId(fileId);
        file.setRequestExperienceId(reqId);
        requestExperienceFileDAO.saveRequestFile(file);
    }
}
