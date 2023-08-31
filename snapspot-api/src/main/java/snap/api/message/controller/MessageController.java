package snap.api.message.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import snap.api.message.dto.request.RefuseRequestDto;
import snap.api.message.service.MessageService;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/refuse")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String createRefuse(@RequestBody RefuseRequestDto requestDto) {
        return messageService.createRefuse(requestDto);
    }

}
