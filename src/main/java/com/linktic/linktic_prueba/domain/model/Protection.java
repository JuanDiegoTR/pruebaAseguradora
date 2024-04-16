package com.linktic.linktic_prueba.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@Builder
public class Protection {
    private Long protectionCode;
    private String protectionName;
    private BigDecimal premiumValue;
}
