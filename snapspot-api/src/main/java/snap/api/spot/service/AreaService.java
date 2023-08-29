package snap.api.spot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.spot.service.AreaDomainService;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaDomainService areaDomainService;

    public AreaResponseDto findArea(Long areaId){
        return new AreaResponseDto(areaDomainService.findArea(areaId));
    }
}
