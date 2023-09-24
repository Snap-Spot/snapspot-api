package snap.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sort {

    SCORE("별점 높은 순"),
    PAY("가격 낮은 순"),
    REVIEW("후기 많은 순");

    private final String name;
}
