package snap.domains.message.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sender {
    MEMBER("고객"),
    PHOTOGRAPHER("사진 작가"),
    SNAPSPOT("스냅스팟");

    private final String name;
}
