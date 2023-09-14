package snap.mail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MailDto {
    private String toEmail;
    private String toName;
    private UUID planId;
    private String message;
    private MultipartFile file;

    @Builder
    public MailDto(String toEmail, String toName, UUID planId, String message, MultipartFile file) {
        this.toEmail = toEmail;
        this.toName = toName;
        this.planId = planId;
        this.message = message;
        this.file = file;
    }
}
