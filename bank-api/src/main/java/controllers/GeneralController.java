package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public interface GeneralController {

    @RequestMapping("/")
    default String mainPage(){
        return "index";
    }
}
