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
    public void applyForExperience(RequestExperienceDTO requestExperienceDTO, List<Long> fileIds) {
        requestExperienceDAO.applyForExperience(requestExperienceDTO);
        Long reqId=requestExperienceDTO.getId();
        List<RequestExperienceFileDTO> dtoList = fileIds.stream()
            .map(fileId -> new RequestExperienceFileDTO(fileId, reqId))
            .toList();
        dtoList.forEach(requestExperienceFileDTO -> {
            requestExperienceFileDAO.saveRequestFile(requestExperienceFileDTO);
        });
    }
}
