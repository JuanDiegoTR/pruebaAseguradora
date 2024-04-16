package com.linktic.linktic_prueba.infrastructure.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "premiums")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PremiumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "protection_code")
    private Long protectionCode;
    @Column(name = "minimum_age")
    private Long minAge;
    @Column(name = "maximum_age")
    private Long maxAge;
    @Column(name = "premium_percentage")
    private BigDecimal premiumPercentage;
}
