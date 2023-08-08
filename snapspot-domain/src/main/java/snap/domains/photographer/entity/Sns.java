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
    private Long snsId;

    @ManyToOne
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;


    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column
    private String account;

    @Builder
    public Sns(Photographer photographer, Type type, String account) {
        this.photographer = photographer;
        this.type = type;
        this.account = account;
    }
}
