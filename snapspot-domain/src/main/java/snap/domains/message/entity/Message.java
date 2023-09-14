package snap.domains.message.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.plan.entity.Plan;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column
    private String contents;

    @Column
    @Enumerated(EnumType.STRING)
    private Sender sender;

    @Builder
    public Message(Plan plan, String contents, Sender sender) {
        this.plan = plan;
        this.contents = contents;
        this.sender = sender;
    }
}
