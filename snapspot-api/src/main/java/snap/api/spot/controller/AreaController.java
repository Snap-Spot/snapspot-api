package snap.api.spot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.spot.dto.AreaResponseDto;
import snap.domains.spot.service.AreaService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/areas")
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/{areaId}")
    public AreaResponseDto getArea(@PathVariable Long areaId){
        return new AreaResponseDto(areaService.findArea(areaId));
    }
}
