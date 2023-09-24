package snap.api.spot.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedResponseDto {
    private List<AreaImageResponseDto> images;
    private int pageNumber;
    private int size;
    private Long total;

    public FeedResponseDto(List<AreaImageResponseDto> images, Pageable pageable) {
        this.pageNumber = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.total = pageable.getOffset();
        this.images = images;
    }
}
