package com.example.kok.dto;

import com.example.kok.enumeration.RequestStatus;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode(of="id")
@NoArgsConstructor
public class AdminPaymentAdvertiseDTO {
    private Long id;
    private long paymentPrice;
    private RequestStatus paymentStatus;
    private LocalDateTime paymentPaidDatetime;
    private long advertisementId;
    private long requestExperienceId;

    private RequestStatus advertisementStatus;
}
