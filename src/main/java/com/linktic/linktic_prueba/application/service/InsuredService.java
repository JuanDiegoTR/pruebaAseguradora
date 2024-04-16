package com.linktic.linktic_prueba.application.service;

import com.linktic.linktic_prueba.application.constant.MessageConstant;
import com.linktic.linktic_prueba.application.port.input.IInsuredService;
import com.linktic.linktic_prueba.application.port.output.IPersistenPort;
import com.linktic.linktic_prueba.domain.model.Insured;
import com.linktic.linktic_prueba.domain.model.Liquidation;
import com.linktic.linktic_prueba.domain.model.Premium;
import com.linktic.linktic_prueba.domain.model.Protection;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.PremiumEntity;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.ProtectionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InsuredService implements IInsuredService {

    private final IPersistenPort iPersistenPort;

    /**
     * This is the main method that calculates the settlement for an insured
     */
    @Override
    public Liquidation calculateLiquidation(Insured insured) {
        validateInsuredValue(insured.getInsuredValue());

        Liquidation liquidation = createLiquidation(insured);
        BigDecimal totalValue = calculateTotalValue(insured, liquidation);

        liquidation.setTotalValue(totalValue);
        return liquidation;
    }

    /**
     * This method validates if the insured value is greater than zero. If not, throw an exception
     */
    private void validateInsuredValue(BigDecimal insuredValue) {
        if (insuredValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(MessageConstant.VALUE_GREATER_THAN_ZERO);
        }
    }

    /**
     * This method creates and returns a new Liquidation object with the policyholder's data
     */
    private Liquidation createLiquidation(Insured insured) {
        return Liquidation.builder()
                .identType(insured.getIdentType())
                .identNumber(insured.getIdentNumber())
                .insuredValue(insured.getInsuredValue())
                .liquidation(new ArrayList<>())
                .build();
    }

    /**
     * This method calculates the total settlement value. First, you get the age of the insured,
     * then you get a list of applicable premiums for that age. It then iterates over each applicable premium,
     * calculates the premium value, adds the corresponding protection to the settlement,
     * and adds the premium value to the total value.
     */
    private BigDecimal calculateTotalValue(Insured insured, Liquidation liquidation) {
        int age = calculateAge(insured.getDateOfBirth());
        List<Premium> applicablePremiums = toModelList(iPersistenPort.premiumList(age));

        return applicablePremiums.stream()
                .map(premium -> {
                    BigDecimal premiumValue = calculatePremiumValue(insured.getInsuredValue(), premium);
                    addProtectionToLiquidation(liquidation, premiumValue, premium);
                    return premiumValue;
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * This method calculates the age of the insured from their date of birth.
     */
    private int calculateAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    /**
     * This method converts a list of PremiumEntity entities to a list of Premium objects
     */
    private List<Premium> toModelList(List<PremiumEntity> premiumEntities) {
        return premiumEntities.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    /**
     * This method converts a PremiumEntity entity to a Premium object
     */
    private Premium toModel(PremiumEntity premiumEntity) {
        return Premium.builder()
                .protectionCode(premiumEntity.getProtectionCode())
                .maxAge(premiumEntity.getMaxAge())
                .minAge(premiumEntity.getMinAge())
                .premiumPercentage(premiumEntity.getPremiumPercentage())
                .build();
    }

    /**
     * This method calculates the value of a premium by multiplying the insured value by the premium percentage.
     */
    private BigDecimal calculatePremiumValue(BigDecimal insuredValue, Premium premium) {
        return insuredValue.multiply(premium.getPremiumPercentage());
    }

    /**
     * This method adds a protection to the list of protections on the Liquidation object.
     */
    private void addProtectionToLiquidation(Liquidation liquidation, BigDecimal premiumValue, Premium premium) {
        Protection protection = toModelProtection(iPersistenPort.findById(premium.getProtectionCode())
                .orElseThrow(() -> new RuntimeException(MessageConstant.AMPARO_NOT_FOUND)));
        liquidation.getLiquidation().add(new Protection(protection.getProtectionCode(), protection.getProtectionName(), premiumValue));
    }

    /**
     * This method converts a ProtectionEntity to a Protection object
     */
    private Protection toModelProtection(ProtectionEntity protection) {
        return Protection.builder()
                .protectionCode(protection.getCode())
                .protectionName(protection.getName())
                .build();
    }

}
