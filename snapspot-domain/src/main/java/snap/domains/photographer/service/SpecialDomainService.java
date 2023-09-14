package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Photographer;
import snap.domains.photographer.entity.Special;
import snap.domains.photographer.repository.SpecialRepository;
import snap.enums.SpecialKeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialDomainService {
    private final SpecialRepository specialRepository;

    public void updateSpecial(Photographer photographer, List<SpecialKeyword> keywordList) {
        List<SpecialKeyword> existedSpecialKeyword = photographer.getSpecialList().stream().map(Special::getKeyword).collect(Collectors.toList());


        List<Special> addList = new ArrayList<>();
        for (SpecialKeyword keyword : keywordList) {
            if (!existedSpecialKeyword.contains(keyword)) {
                addList.add(Special.builder().photographer(photographer).keyword(keyword).build());
            }
        }

        List<Special> removeList = new ArrayList<>();
        for (Special special : photographer.getSpecialList()) {
            if (!keywordList.contains(special.getKeyword())) {
                removeList.add(special);
            }
        }

        specialRepository.saveAll(addList);
        specialRepository.deleteAll(removeList);
    }
}
