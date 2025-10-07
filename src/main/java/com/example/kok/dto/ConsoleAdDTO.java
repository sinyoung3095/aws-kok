package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import com.example.kok.enumeration.Status;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
public class ConsoleAdDTO {
    private Long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private RequestStatus advertisementStatus;
    private String startAdvertise;
    private String endAdvertise;
    private Long companyId;
    private String createdDateTime;
    private String updatedDateTime;

    private List<PostFileDTO> postFiles;
}
