package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PhotographerTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photographerTagId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    public PhotographerTag(Photographer photographer, Tag tag){
        this.photographer = photographer;
        this.tag = tag;
    }
}
