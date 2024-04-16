package com.linktic.linktic_prueba.application.port.input;

import com.linktic.linktic_prueba.domain.model.Insured;
import com.linktic.linktic_prueba.domain.model.Liquidation;

public interface IInsuredService {
    Liquidation calculateLiquidation(Insured insured);
}
