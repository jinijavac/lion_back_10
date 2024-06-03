package org.example.springmvc.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.net.http.HttpRequest;
import java.util.Enumeration;

@Controller
//@SessionAttributes("visitCount2")
public class SessionController {
//
//    @ModelAttribute("visitCount2")
//    public Integer initVisitCount2(){
//        return 0;
//    }
//
//    @GetMapping("/visit2")
//    public String trackVisit(@ModelAttribute("visitCount2") Integer visitCount2, Model model){
//        System.out.println("trackVisit"+visitCount2);
//        visitCount2++;
//        model.addAttribute("visitCount2",visitCount2);
//        return "visit2";
//    }

//    @GetMapping("resetVisit")
//    public String resetVisit(SessionStatus status){
//    //    세션객체 전체 삭제
//        status.setComplete();
//        return "redirect:/visit2";
//    }

    @GetMapping("resetVisit")
    public String resetVisit(HttpSession session, HttpServletRequest request){
        HttpSession session1 = request.getSession();
        System.out.println(session.getAttribute("visitCount"));
        //세션객체에서 해당 속성만 삭제.
//       session.removeAttribute("visitCount2");
        session.setAttribute("visitCount",0);

        System.out.println(session.getAttribute("visitCount"));

        return "redirect:/visit2";
    }



//    HttpSession을 직접 이용한 예.
    @GetMapping("/visit2")
    public String trackVisit(HttpSession session){
        //세션으로 부터 방문 횟수를 얻어온다.
       Integer visitCount =(Integer) session.getAttribute("visitCount");
       if(visitCount == null){
           visitCount = 0;
       }
       visitCount++;
       session.setAttribute("visitCount",visitCount);

       return "visit2";
    }
}
