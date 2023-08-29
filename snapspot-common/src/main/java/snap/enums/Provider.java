package snap.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Provider {
    PROD_SNAPSPOT("PROD_SNAPSPOT", "이메일로 로그인"),
    PROD_KAKAO("PROD_KAKAO", "카카오로 로그인");

    private final String name;
    private final String type;
}
