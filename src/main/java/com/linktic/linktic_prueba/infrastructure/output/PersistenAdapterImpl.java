package com.linktic.linktic_prueba.infrastructure.output;

import com.linktic.linktic_prueba.application.port.output.IPersistenPort;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.PremiumEntity;
import com.linktic.linktic_prueba.infrastructure.output.persistence.entity.ProtectionEntity;
import com.linktic.linktic_prueba.infrastructure.output.persistence.repository.PremiumRepository;
import com.linktic.linktic_prueba.infrastructure.output.persistence.repository.ProtectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersistenAdapterImpl implements IPersistenPort {

    private final PremiumRepository premiumRepository;
    private final ProtectionRepository protectionRepository;

    @Override
    public List<PremiumEntity> premiumList(int age) {
        List<PremiumEntity> premiums = premiumRepository.findPremiumsByAge(age);
        return premiums;
    }

    @Override
    public Optional<ProtectionEntity> findById(Long id) {
        return protectionRepository.findById(id);
    }
}
