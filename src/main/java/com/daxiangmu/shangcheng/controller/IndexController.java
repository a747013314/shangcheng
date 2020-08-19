package com.daxiangmu.shangcheng.controller;


import com.daxiangmu.shangcheng.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@Controller
public class IndexController {

    @Autowired
    private MailService mailService;

    @GetMapping("/login1")
    public String login1(){
        return "/user/login1";
    }

    @GetMapping("/register1")
    public String register1(){
        return "/user/register1";
    }

    @GetMapping("/register2")
    public String register2(){
        return "/user/register2";
    }

    @GetMapping("/register3")
    public String register3(){
        return "/user/register3";
    }

    //发送邮件
    @PostMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(HttpServletRequest request,HttpSession session){
        String email = request.getParameter("email");
        Random random = new Random();
        String verifyCode = String.valueOf(random.nextInt()* (99998 - 10001 + 1) + 10001);
        mailService.sendMail(email,"狗东-忘记密码", verifyCode);
        session.setAttribute("code",verifyCode);
        session.setAttribute("email",email);
        return "success";
    }

    @PostMapping("/verificationCode")
    @ResponseBody
    public String verificationCode(HttpSession session,HttpServletRequest request){
        String email = request.getParameter("email");
        String code = request.getParameter("ch");
        if(email.equals(session.getAttribute("email")) && code.equals(session.getAttribute("code")))
            return "success";
        else
            return "error";
    }
}
