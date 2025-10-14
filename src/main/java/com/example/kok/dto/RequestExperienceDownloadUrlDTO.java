package com.example.kok.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter @ToString
public class RequestExperienceDownloadUrlDTO {
    private List<String> urls;
    private List<String> fileNames;
}
