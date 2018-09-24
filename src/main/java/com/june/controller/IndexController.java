package com.june.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @PostMapping("userLogin")
    public String userLogin(HttpSession session, HttpServletRequest request, String userName, String password) {
        if (!"admin".equals(userName) || !"admin".equals(password)) {
            request.setAttribute("errMsg", "用户名密码错误，登录失败");
            return "login";
        }
        session.setAttribute("userName", userName);
        session.setAttribute("sessionPort", request.getLocalPort());
        session.setAttribute("sessionId", session.getId());
        return "index";
    }

    @GetMapping({"/", "index"})
    public String index(HttpSession session) {
        if (session.getAttribute("userName") != null) {
        	return "index";
        }
        return "login";
    }

}