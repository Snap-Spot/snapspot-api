package snap.domains.photographer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photographer.entity.Tag;
import snap.domains.photographer.repository.TagRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TagDomainService {

    private final TagRepository tagRepository;
}
