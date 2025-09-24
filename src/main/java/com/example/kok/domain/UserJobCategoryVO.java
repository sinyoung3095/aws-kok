package com.example.kok.domain;

import com.example.kok.audit.Period;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(of="id", callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJobCategoryVO{
    private long userId;
    private long jobCategoryId;
}
