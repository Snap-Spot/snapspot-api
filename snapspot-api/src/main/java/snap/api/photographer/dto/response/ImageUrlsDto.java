package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.PhotographerImage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageUrlsDto {
    private List<String> urls;

    /*
    public ImageUrlsDto(List<PhotographerImage> images){
        this.urls = images.stream().map(PhotographerImage::getImage).collect(Collectors.toList());
    }

     */
}
