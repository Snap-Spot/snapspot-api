package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.enums.SpecialKeyword;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Special {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "special_id")
    private Long specialId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialKeyword keyword;

    @Builder
    public Special(Photographer photographer, SpecialKeyword keyword){
        this.photographer = photographer;
        this.keyword = keyword;
    }
}
