package snap.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class MailHandler {
    private JavaMailSender sender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender javaMailSender) throws MessagingException {
        this.sender = javaMailSender;
        message = javaMailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setFrom(String fromAddress) throws MessagingException {
        messageHelper.setFrom(fromAddress);
    }

    public void setTo(String toEmail) throws MessagingException {
        messageHelper.setTo(toEmail);
    }

    public void setTitle(String title) throws  MessagingException {
        messageHelper.setSubject(title);
    }

    public void setContext(String text, boolean useHtml) throws  MessagingException {
        messageHelper.setText(text, useHtml);
    }

    public void setFile(MultipartFile multipartFile) throws MessagingException {
        messageHelper.addAttachment(Objects.requireNonNull(multipartFile.getOriginalFilename()), multipartFile);
    }

    public void send() {
        try {
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
