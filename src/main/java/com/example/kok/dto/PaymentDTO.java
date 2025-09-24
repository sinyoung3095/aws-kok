package com.example.kok.dto;

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
    private long advertisementId;
    private long requestExperienceId;
    private long userId;
    private String createdDateTime;
    private String updatedDateTime;
}
