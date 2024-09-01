package com.webbanhangnongsan.vn.webbanhangnongsan;

import com.webbanhangnongsan.vn.webbanhangnongsan.dto.MailInfo;
import com.webbanhangnongsan.vn.webbanhangnongsan.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class WebbanhangnongsanApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebbanhangnongsanApplication.class, args);
    }
}