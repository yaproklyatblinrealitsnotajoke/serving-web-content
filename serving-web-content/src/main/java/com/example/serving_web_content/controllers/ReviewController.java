package com.example.serving_web_content.controllers;

import com.example.serving_web_content.models.Book;
import com.example.serving_web_content.models.Review;
import com.example.serving_web_content.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.serving_web_content.repo.ReviewRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.serving_web_content.repo.BookRepository;
import com.example.serving_web_content.repo.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


//@Controller
//public class ReviewController {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping("/review")
//    public String reviewMain(Model model){
//        Iterable<Review> reviews = reviewRepository.findAll();
//        model.addAttribute("reviews", reviews);
//        return "review-main";
//    }
//
//    @GetMapping("/review/add")
//    public String reviewAdd(Model model) {
//        Iterable<Book> books = bookRepository.findAll();
//        model.addAttribute("books", books);
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "review-add";
//    }
//
//    @PostMapping("/review/add")
//    public String reviewPostAdd(@RequestParam Long user_id, @RequestParam Long book_id, @RequestParam String rtext, Model model){
//        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
//        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//        Review review = new Review(user, book, rtext);
//        reviewRepository.save(review);
//        return "redirect:/review";
//
//    }
//
//    @GetMapping("/review/{id}")
//    public String reviewDetails(@PathVariable(value = "id") long id, Model model) {
//        if (!reviewRepository.existsById(id)){
//            return "redirect:/review";
//        }
//        Optional<Review> post =  reviewRepository.findById(id);
//        ArrayList<Review> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("review", res);
//        return "review-details";
//    }
//
//    @GetMapping("/review/{id}/edit")
//    public String reviewEdit(@PathVariable(value = "id") long id, Model model) {
//        if (!reviewRepository.existsById(id)){
//            return "redirect:/review";
//        }
//        Optional<Review> post =  reviewRepository.findById(id);
//        ArrayList<Review> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("review", res);
//        return "review-edit";
//    }
//
//    @PostMapping("/review/{id}/edit")
//    public String reviewBookUpdate(@PathVariable(value = "id") long id, @RequestParam User userr, @RequestParam Book book, @RequestParam String rtext, Model model){
//        Review review = reviewRepository.findById(id).orElseThrow();
//        review.setUserr(userr);
//        review.setBook(book);
//        review.setRtext(rtext);
//        reviewRepository.save(review);
//        return "redirect:/review";
//
//    }
//
//    @PostMapping("/review/{id}/remove")
//    public String reviewPostDelete(@PathVariable(value = "id") long id, Model model){
//        Review review = reviewRepository.findById(id).orElseThrow();
//        reviewRepository.delete(review);
//        return "redirect:/review";
//
//    }
//}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/review")
    public String reviewMain(Model model) {
        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "review-main";
    }

    @GetMapping("/review/add")
    public String reviewAdd(Model model) {
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "review-add";
    }

    @PostMapping("/review/add")
    public String reviewPostAdd(@RequestParam Long userid, @RequestParam Long book_id, @RequestParam String rtext, Model model) {
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
        User user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Review review = new Review(user, book, rtext);
        reviewRepository.save(review);
        return "redirect:/review";

    }

    @GetMapping("/review/{id}")
    public String reviewDetails(@PathVariable(value = "id") long id, Model model) {
        if (!reviewRepository.existsById(id)) {
            return "redirect:/review";
        }
        Optional<Review> post = reviewRepository.findById(id);
        ArrayList<Review> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("review", res);
        return "review-details";
    }

    @GetMapping("/review/{id}/edit")
    public String reviewEdit(@PathVariable(value = "id") long id, Model model) {
        if (!reviewRepository.existsById(id)) {
            return "redirect:/review";
        }
        Optional<Review> post = reviewRepository.findById(id);
        ArrayList<Review> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("review", res);

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        return "review-edit";
    }

    @PostMapping("/review/{id}/edit")
    public String reviewBookUpdate(@PathVariable(value = "id") long id, @RequestParam Long userid, @RequestParam Long book_id, @RequestParam String rtext, Model model) {
        Review review = reviewRepository.findById(id).orElseThrow();
        User user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
        review.setUserr(user);
        review.setBook(book);
        review.setRtext(rtext);
        reviewRepository.save(review);
        return "redirect:/review";
    }


    @PostMapping("/review/{id}/remove")
    public String reviewPostDelete(@PathVariable(value = "id") long id, Model model) {
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
        return "redirect:/review";

    }
}