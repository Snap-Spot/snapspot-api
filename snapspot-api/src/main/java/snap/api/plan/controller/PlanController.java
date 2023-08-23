package snap.api.plan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import snap.api.plan.dto.request.PlanRequestDto;
import snap.api.plan.dto.response.PlanResponseDto;
import snap.api.plan.service.PlanService;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponseDto> createRequested(@RequestBody PlanRequestDto requestDto) {
        return new ResponseEntity<>(planService.createRequested(requestDto), HttpStatus.CREATED);
    }


}
