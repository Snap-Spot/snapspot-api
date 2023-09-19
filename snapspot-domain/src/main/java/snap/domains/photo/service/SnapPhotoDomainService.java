package snap.domains.photo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snap.domains.photo.repository.SnapPhotoRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class SnapPhotoDomainService {

    private final SnapPhotoRepository snapPhotoRepository;
}
