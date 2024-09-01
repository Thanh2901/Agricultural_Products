package com.webbanhangnongsan.vn.webbanhangnongsan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailInfo {
    String from;
    String to;
    String subject;
    String body;
    String attachments;

    public MailInfo(String to, String subject, String body) {
        this.from = "thanhvuworkspace@gmail.com";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}

