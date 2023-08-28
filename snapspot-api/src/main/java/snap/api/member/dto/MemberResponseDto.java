package snap.api.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.domains.member.entity.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {
    private String email;
    private String nickname;
    private Role role;

    @Builder
    public MemberResponseDto(Member entity) {
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.nickname = entity.getNickname();
    }
}
