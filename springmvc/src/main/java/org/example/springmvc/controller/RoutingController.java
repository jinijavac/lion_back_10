package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class RoutingController {
    @GetMapping("/start")
    public String startProcess(Model model){
        model.addAttribute("forwardTest","carami");
        System.out.println("startProcess");
        return "forward:/forward";
    }

    @GetMapping("/forward")
    public String forward(Model model,HttpServletRequest request){
        System.out.println("forwardTest:::::"+model.getAttribute("forwardTest"));
        System.out.println(request.getAttribute("forwardTest"));

        System.out.println("forward");
        return "forwardPage";   //forwardPage는 뷰 이름.
    }

    @GetMapping("/redirect")
    public String redirect(Model model){
        System.out.println("redirect");
        model.addAttribute("redirectTest","carami");

        return "redirect:/finalDestination";
    }

    @GetMapping("/finalDestination")
    public String finalDestination(Model model, HttpServletRequest request){
        System.out.println("redirectTest:::::"+model.getAttribute("redirectTest"));

        System.out.println(request.getAttribute("redirectTest"));
        System.out.println("finalDestination");

        return "redirectPage";
    }
}
