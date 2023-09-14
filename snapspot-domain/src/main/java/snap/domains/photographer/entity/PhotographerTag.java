package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.dto.request.TagReq;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PhotographerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photographer_tag_id")
    private Long photographerTagId;

    @OneToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column
    private String tag1;

    @Column
    private String tag2;

    @Column
    private String tag3;

    @Builder
    public PhotographerTag(Photographer photographer) {
        this.photographer = photographer;
    }

    public void update(TagReq tag) {
        this.tag1 = tag.getTag1();
        this.tag2 = tag.getTag2();
        this.tag3 = tag.getTag3();
    }
}
