package com.webbanhangnongsan.vn.webbanhangnongsan.controller;

import com.webbanhangnongsan.vn.webbanhangnongsan.entity.Role;
import com.webbanhangnongsan.vn.webbanhangnongsan.entity.User;
import com.webbanhangnongsan.vn.webbanhangnongsan.repository.UserRepository;
import com.webbanhangnongsan.vn.webbanhangnongsan.service.SendMailService;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterController {
    UserRepository userRepository;
    SendMailService sendMailService;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    HttpSession httpSession;

    @GetMapping("/register")
    public ModelAndView registerForm(ModelMap model) {
        model.addAttribute("user", new User());
        return new ModelAndView("web/register", model);
    }

//    @PostMapping("/register")
//    public String register(ModelMap model, @Validated @ModelAttribute("user") User dto, BindingResult result,
//                           @RequestParam("password") String password) {
//        if(result.hasErrors()) {
//            return "web/register";
//        }
//        if(!checkEmail(dto.getEmail())) {
//            model.addAttribute("error", "Email has been used");
//            return "web/register";
//        }
//        httpSession.removeAttribute("otp");
//        int random_otp = (int) Math.floor(Math.random()*(999999 - 100000 + 1) + 100000);
//        httpSession.setAttribute("otp", random_otp);
//        String body = "Mã OTP của bạn là \n"
//                + random_otp + "\nVui lòng không tiết lộ mã OTP ra ngoài!";
//        sendMailService.queue(dto.getEmail(), "Đăng kí tài khoản", body);
//        model.addAttribute("user", dto);
//        model.addAttribute("message", "Mã xác thực OTP đã được gửi tới Email : " + dto.getEmail() + " , hãy kiểm tra Email của bạn!");
//
//        return "web/confirmOtpRegister";
//    }
    @PostMapping("/register")
    public String register(ModelMap model, @Validated @ModelAttribute("user") User dto, BindingResult result,
                           @RequestParam("password") String password) {
        if (result.hasErrors()) {
            return "web/register";
        }
        if (!checkEmail(dto.getEmail())) {
            model.addAttribute("error", "Email has been used");
            return "web/register";
        }
        httpSession.removeAttribute("otp");
        int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        httpSession.setAttribute("otp", random_otp);

        sendMailService.sendOTP(dto.getEmail(), random_otp);

        model.addAttribute("user", dto);
        model.addAttribute("message", "Mã xác thực OTP đã được gửi tới Email : " + dto.getEmail() + " , hãy kiểm tra Email của bạn!");

        return "web/confirmOtpRegister";
    }


    @PostMapping("/confirmOtpRegister")
    public ModelAndView confirmRegister(ModelMap model, @ModelAttribute("user") User dto,
                                        @RequestParam("password") String password, @RequestParam("otp") String otp) {
        if (otp.equals(String.valueOf(httpSession.getAttribute("otp")))) {
            dto.setPassword(bCryptPasswordEncoder.encode(password));
            dto.setRegister_date(new Date());
            dto.setStatus(true);
            dto.setRoles(Arrays.asList(new Role("ROLE_USER")));
            userRepository.save(dto);

            httpSession.removeAttribute("otp");
            model.addAttribute("message", "Đăng kí thành công");
            return new ModelAndView("web/login");
        }

        model.addAttribute("user", dto);
        model.addAttribute("error", "Mã xác thực OTP không chính xác, hãy thử lại!");
        return new ModelAndView("web/confirmOtpRegister", model);
    }

    // check email
    public boolean checkEmail(String email) {
        List<User> list = userRepository.findAll();
        for (User c : list) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                return false;
            }
        }
        return true;
    }

}
