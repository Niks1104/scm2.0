package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


@Controller
@AllArgsConstructor
public class PageController {

    private final UserService userService;

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

   //contact us route
   @GetMapping("/contact")
   public String contactUsPage() {
        System.out.println("Contact Us page loading");
       return "contact";
   }

   //login route
   @GetMapping("/login")
   public String LoginPage() {
        System.out.println("Login page loading");
       return "login";
   }

   //signup route
   @GetMapping("/register")
   public String SignUpPage(Model model) {

        UserForm userForm = new UserForm();
        //we can send default data as well
        model.addAttribute("userForm", userForm);

        return "register";
   }

   //processing register
   @PostMapping("/do-register")
   public String processRegister(@ModelAttribute UserForm userForm, HttpSession session){
    System.out.println("Processing Registration");

    // fetch the signup form data (User Form)
    System.out.println(userForm);
    // validate form data
    //TODO: 

    // save to database (UserService)
    //take the userform data and insert into user

    // User user = User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .about(userForm.getAbout())
    // .password(userForm.getPassword())
    // .phoneNumber(userForm.getPhoneNumber())
    // .profilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fimage-vector%2Fdefault-avatar-profile-icon-social-media-1913928688&psig=AOvVaw0trLEDxUtNMIALu_NZB46J&ust=1722938060737000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOicsMrK3YcDFQAAAAAdAAAAABAE")
    // .build();

    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setProfilePic("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.shutterstock.com%2Fimage-vector%2Fdefault-avatar-profile-icon-social-media-1913928688&psig=AOvVaw0trLEDxUtNMIALu_NZB46J&ust=1722938060737000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOicsMrK3YcDFQAAAAAdAAAAABAE");


    System.out.println(user);
    User savedUser = userService.saveUser(user);
    System.out.println("User saved");
    // message="Registration Successful"

    //add the message
    Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
    session.setAttribute("message", message);

    // redirect to login page
    return "redirect:/register";
   }
}
