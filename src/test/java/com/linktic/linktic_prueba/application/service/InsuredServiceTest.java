package com.linktic.linktic_prueba.application.service;

import com.linktic.linktic_prueba.application.port.output.IPersistenPort;
import com.linktic.linktic_prueba.domain.model.Insured;
import com.linktic.linktic_prueba.domain.model.Liquidation;
import com.linktic.linktic_prueba.domain.model.Protection;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.PremiumEntity;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.ProtectionEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InsuredServiceTest {

    @InjectMocks private InsuredService insuredService;
    @Mock private IPersistenPort iPersistenPort;

    @Test
    void calculateLiquidation() {
        Insured insured = Insured.builder()
                .identType(1L)
                .identNumber("51000002")
                .insuredValue(BigDecimal.ONE)
                .dateOfBirth(LocalDate.parse("2003-03-22"))
                .build();

        List<PremiumEntity> premiumEntities = new ArrayList<>();
        PremiumEntity premiumEntity = new PremiumEntity();
        premiumEntity.setId(1L);
        premiumEntity.setMaxAge(45L);
        premiumEntity.setMinAge(18L);
        premiumEntity.setPremiumPercentage(BigDecimal.valueOf(0.02));
        premiumEntity.setProtectionCode(1L);
        premiumEntities.add(premiumEntity);
        Mockito.doReturn(premiumEntities).when(iPersistenPort).premiumList(21);

        ProtectionEntity protection = new ProtectionEntity();
        protection.setId(1L);
        protection.setCode(1L);
        protection.setName("Muerte accidental");
        Mockito.when(iPersistenPort.findById(1L)).thenReturn(Optional.of(protection));

        Liquidation result = insuredService.calculateLiquidation(insured);
        Assertions.assertNotNull(result);
    }
}