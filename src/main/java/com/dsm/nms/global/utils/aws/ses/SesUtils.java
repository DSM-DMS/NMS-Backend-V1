package com.dsm.nms.global.utils.aws.ses;

import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SesUtils {

    @Value("${aws.ses.baseEmail}")
    private String from;

    private final SesConfig sesConfig;

    public void sendEmail(String to, String authCode) {
        Message message = new Message()
                .withSubject(createContent("NMS 이메일 인증"))
                .withBody(new Body()
                        .withHtml(createContent(authCode)));

        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withSource(from)
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(message);

        sesConfig.amazonSimpleEmailService().sendEmail(sendEmailRequest);
    }

    private Content createContent(String text) {
        return new Content()
                .withCharset("UTF-8")
                .withData(text);
    }
}
