package com.example.kok.dto;

import com.example.kok.util.Criteria;
import com.example.kok.util.Search;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
public class AdminExperienceCriteriaDTO {
    private List<AdminExperienceDTO> experienceList;
    private Criteria criteria;
}
