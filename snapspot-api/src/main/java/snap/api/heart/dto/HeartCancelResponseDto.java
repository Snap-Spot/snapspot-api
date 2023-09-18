package snap.api.heart.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.photographer.entity.Photographer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartCancelResponseDto {

    private String memberName;
    private String photographerName;
    private String message;

    public HeartCancelResponseDto(Member member, Photographer photographer){
        this.memberName = member.getNickname();
        this.photographerName = photographer.getMember().getNickname();
        this.message = "좋아요를 취소했습니다.";
    }
}
