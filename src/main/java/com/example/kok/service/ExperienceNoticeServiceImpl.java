package com.example.kok.service;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.util.Criteria;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ExperienceNoticeServiceImpl implements ExperienceNoticeService {
    private ExperienceNoticeDAO experienceNoticeDAO;

    @Override
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page) {
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = new ExperienceNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, experienceNoticeDAO.findCountAll());
        List<ExperienceNoticeDTO> experiences=experienceNoticeDAO.findAll(criteria);
        experiences.forEach(experience -> {
            LocalDate endDate = experience.getExperienceEndDate();
            LocalDate today = LocalDate.now();
            if (endDate.isBefore(today)) {
                long days = ChronoUnit.DAYS.between(today, endDate);
                experience.setRemainingDays(days);
            } else {
                experience.setRemainingDays(0L); // endDate보다 today가 이전일 경우 0
            }
        });

        if(criteria.isHasMore()){
            experiences.remove(experiences.size() - 1);
        }

        experienceNoticeCriteriaDTO.setExperiences(experiences);
        experienceNoticeCriteriaDTO.setCriteria(criteria);
        return experienceNoticeCriteriaDTO;
    }
}
