package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.Special;
import snap.enums.SpecialKeyword;
import snap.domains.photographer.repository.SpecialRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialDomainService {
    private final SpecialRepository specialRepository;

    public void updateSpecial(Photographer photographer, List<SpecialKeyword> specialList) {
        specialList.stream()
                .map(keyword -> specialRepository.save(
                        Special.builder()
                                .photographer(photographer)
                                .keyword(keyword)
                                .build()
                ))
                .collect(Collectors.toList());
    }
}
