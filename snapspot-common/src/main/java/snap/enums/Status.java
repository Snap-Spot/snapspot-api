package snap.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {
    REQUEST("STATUS_REQUEST", "예약 신청"),
    DEPOSIT("STATUS_DEPOSIT", "입금 요청"),
    REFUSE("STATUS_REFUSE", "예약 거절"),
    RESERVED("STATUS_RESERVED", "예약 완료"),
    TODAY("STATUS_TODAY", "촬영 진행"),
    COMPLETE("STATUS_COMPLETE","촬영 완료"),
    DELIVERY("STATUS_DELIVERY", "사진 전달"),
    CANCEL("STATUS_CANCEL", "예약 취소");

    private final String label;
    private final String detail;
}
