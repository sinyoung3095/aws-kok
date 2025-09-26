package com.example.kok.service;

import com.example.kok.dto.ExperienceNoticeCriteriaDTO;
import com.example.kok.dto.ExperienceNoticeDTO;
import com.example.kok.repository.ExperienceNoticeDAO;
import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.DateUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceNoticeServiceImpl implements ExperienceNoticeService {
    private final ExperienceNoticeDAO experienceNoticeDAO;

    @Override
    public ExperienceNoticeCriteriaDTO selectAllExperienceNotice(int page, Search search) {
        System.out.println("서비스임플 실행해용");
        ExperienceNoticeCriteriaDTO experienceNoticeCriteriaDTO = new ExperienceNoticeCriteriaDTO();
        Criteria criteria = new Criteria(page, experienceNoticeDAO.findCountAll());
        List<ExperienceNoticeDTO> experiences=experienceNoticeDAO.findAll(criteria, search);
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
