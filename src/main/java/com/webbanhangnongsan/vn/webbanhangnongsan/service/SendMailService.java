package com.webbanhangnongsan.vn.webbanhangnongsan.service;

import com.webbanhangnongsan.vn.webbanhangnongsan.dto.MailInfo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SendMailService {
    @Autowired
    private final JavaMailSender mailSender;
    private List<MailInfo> mailInfoList = new ArrayList<>();
    private final TemplateEngine templateEngine;

    public void sendEmail(MailInfo mailInfo) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailInfo.getTo());
            helper.setSubject(mailInfo.getSubject());
            helper.setText(mailInfo.getBody(), true);
            helper.setReplyTo(mailInfo.getFrom());
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace(); // Xử lý lỗi ở đây, hoặc ghi log lỗi
        }
    }

//    public void sendMail(MailInfo mailInfo) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("thanhvuworkspace@gmail.com");
//        message.setTo(mailInfo.getTo());
//        message.setSubject(mailInfo.getSubject());
//        message.setText(mailInfo.getBody());
//        mailSender.send(message);
//        System.out.println("Mail sent successfully");
//        System.out.println(mailInfoList.getFirst());
//    }

    public void queue(String toEmail, String subject, String body) {
        MailInfo mailInfo = new MailInfo(toEmail, subject, body);
        mailInfoList.add(mailInfo);
        this.sendEmail(mailInfo);
    }

    public void sendOTP(String to, int otp) {
        Context context = new Context();
        context.setVariable("otp", otp);
        String body = templateEngine.process("mail/otp-register.html", context);
        queue(to, "Đăng ký tài khoản", body);
    }
}
