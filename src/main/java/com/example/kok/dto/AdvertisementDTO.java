package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdvertisementDTO {
    private long id;
    private String advertisementMainText;
    private String advertisementSubText;
    private RequestStatus advertisementStatus;
    private String advertiseStartDatetime;
    private String advertiseEndDatetime;
    private long companyId;
    private String createdDateTime;
    private String updatedDateTime;
}
