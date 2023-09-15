package snap.api.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.member.entity.Member;
import snap.enums.Role;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private String email;
    private String nickname;
    private String profile;
    private Role role;

    public MemberResponseDto(Member entity) {
        this.email = entity.getEmail();
        this.role = entity.getRole();
        this.profile = entity.getProfileImage();
        this.nickname = entity.getNickname();
    }
}
