package snap.domains.plan.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    REQUESTED("STATUS_REQUESTED", "예약 신청"),
    RESERVED("STATUS_RESERVED", "예약 완료"),
    PHOTOGRAPHING("STATUS_PHOTOGRAPHING", "촬영 진행"),
    COMPLETED("STATUS_COMPLETED", "사진 전달 완료"),
    CANCEL("STATUS_CANCEL", "취소됨");

    private final String name;
    private final String detail;
}
