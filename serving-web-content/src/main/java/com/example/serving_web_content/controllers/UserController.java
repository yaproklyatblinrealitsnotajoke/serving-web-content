package com.example.serving_web_content.controllers;


import com.example.serving_web_content.models.User;
import com.example.serving_web_content.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user")
    public String userMain(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user-main";
    }

    @GetMapping("/user/add")
    public String userAdd(Model model) {
        return "user-add";
    }

    @PostMapping("/user/add")
    public String userPostAdd(@RequestParam String user_name, @RequestParam String email, Model model){
        User user = new User(user_name, email);
        userRepository.save(user);
        return "redirect:/";

    }

    @GetMapping("/user/{id}")
    public String userDetails(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)){
            return "redirect:/user";
        }
        Optional<User> post =  userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-details";
    }

    @GetMapping("/user/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)){
            return "redirect:/user";
        }
        Optional<User> post =  userRepository.findById(id);
        ArrayList<User> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("user", res);
        return "user-edit";
    }

    @PostMapping("/user/{id}/edit")
    public String userBookUpdate(@PathVariable(value = "id") long id, @RequestParam String user_name, @RequestParam String email, Model model){
        User user = userRepository.findById(id).orElseThrow();
        user.setUser_name(user_name);
        user.setEmail(email);
        userRepository.save(user);
        return "redirect:/user";

    }

    @PostMapping("/user/{id}/remove")
    public String userPostDelete(@PathVariable(value = "id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "redirect:/user";

    }
}
