package com.example.serving_web_content.controllers;

import com.example.serving_web_content.models.Author;
import com.example.serving_web_content.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.serving_web_content.repo.BookRepository;
import com.example.serving_web_content.repo.AuthorRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @GetMapping("/book")
    public String bookMain(Model model){
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book-main";
    }

    @GetMapping("/book/add")
    public String bookAdd(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "book-add";
    }

    @PostMapping("/book/add")
    public String blogPostAdd(@RequestParam String book_name, @RequestParam String genre, @RequestParam Long author_id, Model model){
        Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID"));
        Book book = new Book(book_name, genre, author);
        bookRepository.save(book);
        return "redirect:/book";
    }

    @GetMapping("/book/{id}")
    public String bookDetails(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)){
            return "redirect:/book";
        }
        Optional<Book> post =  bookRepository.findById(id);
        ArrayList<Book> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("book", res);
        return "book-details";
    }

    @GetMapping("/book/{id}/edit")
    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
        if (!bookRepository.existsById(id)) {
            return "redirect:/book";
        }
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            model.addAttribute("book", book);
            Iterable<Author> authors = authorRepository.findAll();
            model.addAttribute("authors", authors);
            return "book-edit";

        } else {
            return "redirect:/book";
        }

    }

//    @GetMapping("/book/{id}/edit")
//    public String bookEdit(@PathVariable(value = "id") long id, Model model) {
//        if (!bookRepository.existsById(id)){
//            return "redirect:/book";
//        }
//        Optional<Book> post =  bookRepository.findById(id);
//        ArrayList<Book> res = new ArrayList<>();
//        post.ifPresent(res::add);
//        model.addAttribute("book", res);
//        return "book-edit";
//    }

    @PostMapping("/book/{id}/edit")
    public String bookBookUpdate(@PathVariable(value = "id") long id, @RequestParam String book_name, @RequestParam String genre, @RequestParam Long author_id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + id));
        Author author = authorRepository.findById(author_id).orElseThrow(() -> new IllegalArgumentException("Invalid author ID: " + author_id));

        book.setBook_name(book_name);
        book.setGenre(genre);
        book.setAuthor(author);
        bookRepository.save(book);
        return "redirect:/book";
    }



//    @PostMapping("/book/{id}/edit")
//    public String bookBookUpdate(@PathVariable(value = "id") long id, @RequestParam String book_name, @RequestParam String genre, @RequestParam Author author, Model model){
//        Book book = bookRepository.findById(id).orElseThrow();
//        book.setBook_name(book_name);
//        book.setGenre(genre);
//        book.setAuthor(author);
//        bookRepository.save(book);
//        return "redirect:/book";
//
//    }

    @PostMapping("/book/{id}/remove")
    public String bookPostDelete(@PathVariable(value = "id") long id, Model model){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
        return "redirect:/book";

    }
}
