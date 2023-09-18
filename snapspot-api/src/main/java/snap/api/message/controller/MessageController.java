package snap.api.message.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import snap.api.message.dto.MessageRequestDto;
import snap.api.message.dto.MessageResponseDto;
import snap.api.message.service.MessageService;
import snap.domains.member.entity.Member;
import snap.resolver.AuthMember;
import snap.response.SuccessResponse;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<SuccessResponse> sendMessage(@AuthMember Member member, @RequestBody MessageRequestDto request) {
        messageService.createMessage(member, request);
        return ResponseEntity.ok(
                SuccessResponse
                .builder().status(200).code("OK").message("메세지가 성공적으로 전송되었습니다.").details("메세지가 성공적으로 전송되었습니다.").build());
    }

    @GetMapping("/{planId}")
    public ResponseEntity<List<MessageResponseDto>> getMessages(@AuthMember Member member, @PathVariable UUID planId) {
        return ResponseEntity.ok(messageService.getMessages(member, planId));
    }


}
