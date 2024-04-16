package com.linktic.linktic_prueba.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Premium {
    private Long protectionCode;
    private Long minAge;
    private Long maxAge;
    private BigDecimal premiumPercentage;
}
