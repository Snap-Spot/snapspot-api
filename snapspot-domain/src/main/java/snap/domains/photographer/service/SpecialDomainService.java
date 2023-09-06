package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.Special;
import snap.domains.photographer.repository.SpecialRepository;
import snap.enums.SpecialKeyword;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialDomainService {
    private final SpecialRepository specialRepository;

    public void updateSpecial(Photographer photographer, List<SpecialKeyword> keywordList) {
        List<Special> oldList = specialRepository.findAllByPhotographer(photographer);
        specialRepository.deleteAll(oldList);

        keywordList.forEach(keyword -> specialRepository.save(
                Special.builder().photographer(photographer).keyword(keyword).build()
        ));
    }
}
