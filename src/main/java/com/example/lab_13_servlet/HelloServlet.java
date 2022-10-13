package com.example.lab_13_servlet;

import java.io.*;

import Data.DataBase;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("firstname");
        String number = request.getParameter("number");


        if (number.length() > 1)
            DataBase.addUser(name, number);


        response.sendRedirect("/Lab_13_servlet_war_exploded/index.jsp");
    }

    public void destroy() {
    }
}