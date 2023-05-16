package jmu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class jspController {

    @GetMapping("/jsp")
    public String jsp(Model model, String name){
        model.addAttribute("name",name);
        return "WEB-INF/jsp/jsp.jsp";
    }
}

