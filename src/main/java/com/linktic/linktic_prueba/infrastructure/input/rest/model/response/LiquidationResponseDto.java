package com.linktic.linktic_prueba.infrastructure.input.rest.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class LiquidationResponseDto {

    private Long identType;
    private String identNumber;
    private BigDecimal insuredValue;
    private List<ProtectionResponseDto> liquidation;
    private BigDecimal totalValue;
}
