package com.linktic.linktic_prueba.infrastructure.input.rest.mapper;

import com.linktic.linktic_prueba.domain.model.Insured;
import com.linktic.linktic_prueba.domain.model.Liquidation;
import com.linktic.linktic_prueba.domain.model.Protection;
import com.linktic.linktic_prueba.infrastructure.input.rest.model.request.InsuredRequestDto;
import com.linktic.linktic_prueba.infrastructure.input.rest.model.response.LiquidationResponseDto;
import com.linktic.linktic_prueba.infrastructure.input.rest.model.response.ProtectionResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InsuredRestMapper {

    public Insured toInsured(InsuredRequestDto insuredRequestDto){
        return Insured.builder()
                .identType(insuredRequestDto.getIdentType())
                .dateOfBirth(insuredRequestDto.getDateOfBirth())
                .identNumber(insuredRequestDto.getIdentNumber())
                .insuredValue(insuredRequestDto.getInsuredAmount())
                .build();
    };

    public LiquidationResponseDto toLiquidationResponse(Liquidation liquidation){
        return LiquidationResponseDto.builder()
                .identType(liquidation.getIdentType())
                .identNumber(liquidation.getIdentNumber())
                .insuredValue(liquidation.getInsuredValue())
                .liquidation(protectionResponseDtos(liquidation.getLiquidation()))
                .totalValue(liquidation.getTotalValue())
                .build();
    };

    public List<ProtectionResponseDto> protectionResponseDtos(List<Protection> protections){
        return protections.stream()
                .map(this::responseDto)
                .collect(Collectors.toList());
    }

    public ProtectionResponseDto responseDto(Protection protection){
        return ProtectionResponseDto.builder()
                .premiumValue(protection.getPremiumValue())
                .protectionCode(protection.getProtectionCode())
                .protectionName(protection.getProtectionName())
                .build();
    }
}
