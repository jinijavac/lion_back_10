package org.example.filterexam3;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/loginform")
    public String loginform(){
        return "loginform";
    }

    @PostMapping("/login")
    public String loginform(@ModelAttribute("user") User user, HttpServletResponse response) {

        //사용자가 보낸 username과 패스워드가 서버가 원하는 정보랑 일치하는지 확인하고
        //사용자 정보를 유지하게 하면 된다.
        if(user.getUsername().equals("carami") && user.getPassword().equals("1234")){
            //실제 서비스에서는 아이디 암호를 검사해서 진행하겠지만...   여기서는 간단하게..
            Cookie cookie = new Cookie("auth","carami");
            cookie.setPath("/");

            //이렇게 생성된 쿠키는 클라이언에게 보내져야한다.
            response.addCookie(cookie);
        }

        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String welcome(){

        User user = UserContext.getUser();
        if(user != null)
            return "welcome";
        else
            return "redirect:/loginform";

    }

    @GetMapping("/info")
    public String info(HttpServletRequest request){
        //아무나 보여줄 수 있는 페이지는 아니고,
        //로그인한 사용자에게만 보여줄거다.
        //쿠키정보를 통해서 알 수 있을거예요.
//        String auth = null;
//        Cookie[] cookies = request.getCookies();
//        if(cookies != null){
//            for(Cookie cookie: cookies){
//                if(cookie.getName().equals("auth")){
//                    auth = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        if(auth != null)  //해당 사용자가 로그인한 사용자인지 아닌지에 따라서..
//            return "info";
//        else
//            return "redirect:/loginform";
        User user = UserContext.getUser();
        if(user != null)
            return "info";
        else
            return "redirect:/loginform";
    }
}
