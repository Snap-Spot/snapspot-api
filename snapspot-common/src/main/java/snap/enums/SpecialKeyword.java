package snap.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SpecialKeyword {
    COUPLE("커플스냅"),
    FRIEND("우정스냅"),
    GRADUATION("졸업스냅"),
    WEDDING("웨딩스냅"),
    FAMILY("가족스냅");

    private final String name;
}
