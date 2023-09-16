package snap.domains.review.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Plan;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column
    private Integer score;

    @Column
    private String comment;

    @Column
    private String image;

    @Builder
    public Review(Plan plan, Integer score, String comment, String image) {
        this.plan = plan;
        this.score = score;
        this.comment = comment;
        this.image = image;
    }
}
