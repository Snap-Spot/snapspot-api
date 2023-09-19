package snap.api.photo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.domains.photo.service.SnapPhotoDomainService;

@Service
@RequiredArgsConstructor
public class SnapPhotoService {

    private final SnapPhotoDomainService snapPhotoDomainService;
}
