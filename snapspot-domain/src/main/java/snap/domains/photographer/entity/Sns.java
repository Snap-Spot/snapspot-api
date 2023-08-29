package snap.domains.photographer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Sns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sns_id")
    private Long snsId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    @Column
    @Enumerated(EnumType.STRING)
    private SnsType type;

    @Column
    private String account;

    @Builder
    public Sns(Photographer photographer, SnsType type, String account) {
        this.photographer = photographer;
        this.type = type;
        this.account = account;
    }
}
