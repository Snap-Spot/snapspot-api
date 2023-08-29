package snap.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    ROLE_MEMBER("ROLE_MEMBER", "일반 고객 사용자"),
    ROLE_PHOTOGRAPHER("ROLE_PHOTOGRAPHER", "사진 작가 사용자");

    private final String name;
    private final String type;
}
