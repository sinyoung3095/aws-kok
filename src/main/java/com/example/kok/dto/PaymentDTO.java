package com.example.kok.dto;

import com.example.kok.enumeration.Status;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class PaymentDTO {
    private long id;
    private long paymentPrice;
    private Status paymentStatus;
    private String paymentPaidDatetime;
    private long advertisementId;
    private long requestExperienceId;
    private long userId;
    private String createdDateTime;
    private String updatedDateTime;

    private String experienceNoticeTitle;
    private String requestExperienceStatus;
    private String companyName;
}
