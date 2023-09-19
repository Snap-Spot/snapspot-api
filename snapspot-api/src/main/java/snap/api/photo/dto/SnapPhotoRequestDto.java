package snap.api.photo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnapPhotoRequestDto {
    private String imageUrl;
    private LocalDateTime photoDate;
    private String location;
    private String photographerName;
    private String tag;
}
