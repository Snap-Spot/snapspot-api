package snap.domains.photographer.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Type {
    INSTAGRAM("INSTAGRAM"),
    KAKAO_CHANNEL("KAKAO_CHANNEL"),
    FACEBOOK("FACEBOOK"),
    TWITTER("TWITTER");

    private final String name;
}
