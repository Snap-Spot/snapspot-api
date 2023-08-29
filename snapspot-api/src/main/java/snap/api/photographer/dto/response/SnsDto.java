package snap.api.photographer.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import snap.domains.photographer.entity.Sns;
import snap.domains.photographer.entity.SnsType;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SnsDto {
    private SnsType type;
    private String account;

    public SnsDto(Sns sns){
        this.type = sns.getType();
        this.account = sns.getAccount();
    }
}

