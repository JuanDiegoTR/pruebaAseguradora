package com.linktic.linktic_prueba.infrastructure.input.rest.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class InsuredRequestDto {
    @NotNull
    private Long identType;
    @NotBlank
    private String identNumber;
    @NotNull
    private BigDecimal insuredAmount;
    private LocalDate dateOfBirth;

}
