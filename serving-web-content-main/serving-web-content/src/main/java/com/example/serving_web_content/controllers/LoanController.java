//package com.example.serving_web_content.controllers;
//import com.example.serving_web_content.models.Author;
//import com.example.serving_web_content.models.Book;
//import com.example.serving_web_content.models.Loan;
//import com.example.serving_web_content.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.ui.Model;
//import com.example.serving_web_content.repo.LoanRepository;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import com.example.serving_web_content.repo.BookRepository;
//import com.example.serving_web_content.repo.UserRepository;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Optional;
//
//@Controller
//public class LoanController {
//    @Autowired
//    private LoanRepository loanRepository;
//    @Autowired
//    private BookRepository bookRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping("/loan")
//    public String loanMain(Model model){
//        Iterable<Loan> loans = loanRepository.findAll();
//        model.addAttribute("loans", loans);
//        return "loan-main";
//    }
//
//    @GetMapping("/loan//add")//add
//    public String loanAdd(Model model) {
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        Iterable<Book> books = bookRepository.findAll();
//        model.addAttribute("books", books);
//        return "loan-main";
//    }
//
//
//    @PostMapping("/loan//add")//add
//    public String loanPostAdd(@RequestParam Long userid, @RequestParam Long book_id, @RequestParam LocalDate loan_date, @RequestParam LocalDate return_date, Model model) {
//        User users = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
//        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));
//        Loan loan = new Loan(users, book, loan_date, return_date);
//        loanRepository.save(loan);
//        return "redirect:/loan";
//    }
//
//    @GetMapping("/loan/{id}")
//    public String loanDetails(@PathVariable(value = "id") long id, Model model) {
//        if (!loanRepository.existsById(id)){
//            return "redirect:/loan";
//        }
//        Optional<Loan> post =  loanRepository.findById(id);
//        ArrayList<Loan> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("loan", res);
//        return "loan-details";
//    }
//
//    @GetMapping("/loan/{id}/edit")
//    public String loanEdit(@PathVariable(value = "id") long id, Model model) {
//        if (!loanRepository.existsById(id)){
//            return "redirect:/loan";
//        }
//        Optional<Loan> post =  loanRepository.findById(id);
//        ArrayList<Loan> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("loan", res);
//        return "loan-edit";
//    }
//
//    @PostMapping("/loan/{id}/edit")
//    public String loanBookUpdate(@PathVariable(value = "id") long id, @RequestParam Long userid, @RequestParam Long book_id, @RequestParam LocalDate loan_date, @RequestParam LocalDate return_date, Model model){
//        Loan loan = loanRepository.findById(id).orElseThrow();
//        User user = userRepository.findById(userid).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + userid));
//        Book book = bookRepository.findById(book_id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + book_id));
//
//        loan.setUserl(user);
//        loan.setBook(book);
//        loan.setLoan_date(loan_date);
//        loan.setReturn_date(return_date);
//        loanRepository.save(loan);
//        return "redirect:/loan";
//
//    }
//
//    @PostMapping("/loan/{id}/remove")
//    public String loanPostDelete(@PathVariable(value = "id") long id, Model model){
//        Loan loan = loanRepository.findById(id).orElseThrow();
//        loanRepository.delete(loan);
//        return "redirect:/loan";
//
//    }
//
//}
