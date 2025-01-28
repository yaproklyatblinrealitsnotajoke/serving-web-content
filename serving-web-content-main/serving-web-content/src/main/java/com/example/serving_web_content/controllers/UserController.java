package com.example.serving_web_content.controllers;

import com.example.serving_web_content.models.Author;
import com.example.serving_web_content.models.Book;
import com.example.serving_web_content.models.User;
import com.example.serving_web_content.repo.BookRepository;
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
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/user")
    public String userMain(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user-main";
    }

    @GetMapping("/user/add")//add
    public String userAdd(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "user-add";
    }

    @PostMapping("/user/add")//add
    public String userPostAdd(@RequestParam String user_name, @RequestParam String email, @RequestParam Long book_id, Model model){
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
        User user = new User(user_name, email, book);
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
    public String userEdit(@PathVariable(value = "id") long id, Model model) {
        if (!userRepository.existsById(id)) {
            return "redirect:/user";
        }
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("user", user);
            Iterable<Book> books = bookRepository.findAll();
            model.addAttribute("books", books);
            return "user-edit";

        } else {
            return "redirect:/user";
        }

    }

    @PostMapping("/user/{id}/edit")
    public String userUserUpdate(@PathVariable(value = "id") long id, @RequestParam String user_name, @RequestParam String email, @RequestParam Long book_id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + book_id));

        user.setUser_name(user_name);
        user.setEmail(email);
        user.setBook(book);
        userRepository.save(user);
        return "redirect:/user";
    }


    @PostMapping("/user/{id}/remove")
    public String userPostDelete(@PathVariable(value = "id") long id, Model model){
        User user = userRepository.findById(id).orElseThrow();
        Book book = user.getBook();

        if(book != null) {
            // 2. Удаляем пользователя из списка users книги
            book.getUsers().remove(user);

            // 3. Сохраняем изменения в книге
            bookRepository.save(book);
        }
        userRepository.delete(user);
        return "redirect:/user";

    }
}
