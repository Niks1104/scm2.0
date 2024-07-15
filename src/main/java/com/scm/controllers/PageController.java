package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @RequestMapping("/home")
   public String home(Model model){
        System.out.println("Home Page handler");

        //sending dynamic data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtube", "learn code with durgesh");
        model.addAttribute("gitRepo", "https://github.com/Niks1104");
       return "home";
   }

   //about route
   @RequestMapping("/about")
   public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
       return "about";
   }
   

   //services route
   @RequestMapping("/services")
   public String servicesPage() {
        System.out.println("Services page loading");
       return "services";
   }
}
