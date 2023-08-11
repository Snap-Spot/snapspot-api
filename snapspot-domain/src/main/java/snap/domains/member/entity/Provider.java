package snap.domains.member.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Provider {
    SNAP_SPOT("일반", "GENERAL"),
    KAKAO("카카오", "OAUTH");

    private final String name;
    private final String type;
}
