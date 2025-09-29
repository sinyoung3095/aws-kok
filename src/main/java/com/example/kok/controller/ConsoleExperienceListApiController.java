package com.example.kok.controller;

import com.example.kok.dto.ConsoleExperienceListCriteriaDTO;
import com.example.kok.dto.ConsoleExperienceListDTO;
import com.example.kok.service.ConsoleExperienceListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/enterprise-console/experience")
public class ConsoleExperienceListApiController {
    private final ConsoleExperienceListService experienceService;

    @GetMapping("/list/{companyId}/{page}")
    public ResponseEntity<?> list(@PathVariable("companyId") Long companyId, @PathVariable("page") int page) {
        ConsoleExperienceListCriteriaDTO experienceCriteriaDTO = experienceService.getList(companyId, page);
        if(experienceCriteriaDTO == null || experienceCriteriaDTO.getExperienceLists().size() == 0){
            return ResponseEntity.ok(experienceCriteriaDTO);
        }
        return ResponseEntity.ok(experienceCriteriaDTO);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable("id") Long id,
                                          @RequestBody ConsoleExperienceListDTO consoleExperienceDTO) {
        experienceService.updateListStatus(id, consoleExperienceDTO.getExperienceNoticeStatus());
        return ResponseEntity.ok().body(consoleExperienceDTO);
    }
}
