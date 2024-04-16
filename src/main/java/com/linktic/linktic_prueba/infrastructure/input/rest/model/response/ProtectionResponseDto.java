package com.linktic.linktic_prueba.infrastructure.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@Builder
public class ProtectionResponseDto {
    private Long protectionCode;
    private String protectionName;
    private BigDecimal premiumValue;
}
