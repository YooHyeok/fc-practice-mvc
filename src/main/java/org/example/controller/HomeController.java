package org.example.controller;

import org.example.annotation.Controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}
