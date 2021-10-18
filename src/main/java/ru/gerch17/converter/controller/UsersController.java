package ru.gerch17.converter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gerch17.converter.service.UsersService;

@Controller
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/registrationAction")
    public String addUser(@RequestParam String userName, @RequestParam String password){
        if(usersService.findOneUserByLogin(userName) || userName == "" || password == ""){
            return "redirect:/errorRegistration";
        }else {
            usersService.createUser(userName, password);
            return "redirect:/";
        }
    }

    @GetMapping("/errorRegistration")
    public String errorRegistration(Model model){
        model.addAttribute("UserAlreadyExist","Пользователь с таким логином уже существует или некорректный ввод данных");
        return "registration";
    }

    @GetMapping("/login")
    public String authorizationPage(){
        return "login";
    }

}
