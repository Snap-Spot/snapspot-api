package snap.domains.member.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Provider {
    SNAP_SPOT("SNAP_SPOT", "GENERAL"),
    KAKAO("KAKAO", "OAUTH");

    private final String name;
    private final String type;
}
