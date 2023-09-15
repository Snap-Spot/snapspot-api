package snap.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import snap.enums.SpecialKeyword;

import java.time.LocalDateTime;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotographerFilterReq {
    //지역
    private Long areaId;
    //날짜
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime ableDate;
    //전문분야
    private SpecialKeyword special;
    //페이지
    private Integer page;
}
